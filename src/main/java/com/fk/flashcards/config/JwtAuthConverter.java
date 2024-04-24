package com.fk.flashcards.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

  @Value("${jwt.auth.converter.resource-id}")
  private String resourceId;

  @Value("${jwt.auth.converter.principle-attribute}")
  private String principleAttribute;

  @Override
  public AbstractAuthenticationToken convert(Jwt source) {
    Collection<GrantedAuthority> authorities = Stream
        .concat(jwtGrantedAuthoritiesConverter.convert(source).stream(), extractRoles(source).stream())
        .collect(Collectors.toSet());
    return new JwtAuthenticationToken(source, authorities, getPrincipleClaimName(source));
  }

  private String getPrincipleClaimName(Jwt source) {
    String name = principleAttribute != null ? principleAttribute : JwtClaimNames.SUB;
    return source.getClaim(name);

  }

  private Collection<? extends GrantedAuthority> extractRoles(Jwt source) {
    Map<String, Object> resourceAccess = source.getClaim("resource_access");
    Map<String, Object> resource;
    Collection<String> resourceRoles;

    if (resourceAccess == null) {
      return Set.of();
    }

    resource = (Map<String, Object>) resourceAccess.get(resourceId);
    resourceRoles = (Collection<String>) resource.get("roles");
    return resourceRoles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r)).collect(Collectors.toSet());
  }

}
