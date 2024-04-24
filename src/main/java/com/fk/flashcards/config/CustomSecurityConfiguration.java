package com.fk.flashcards.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class CustomSecurityConfiguration {

  private final JwtAuthConverter jwtAuthConverter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.GET, "/container/**").hasRole("container.read")
            .requestMatchers(
                new MultipleHttpMethods(List.of(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE), "/container/**"))
            .hasRole("container.edit")
            .requestMatchers(HttpMethod.GET, "/flashcard/**").hasRole("flashcard.read")
            .requestMatchers(
                new MultipleHttpMethods(List.of(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE), "/flashcard/**"))
            .hasRole("flashcard.edit")
            .requestMatchers(HttpMethod.GET, "/topic/**").hasRole("topic.read")
            .requestMatchers(
                new MultipleHttpMethods(List.of(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE), "/topic/**"))
            .hasRole("topic.edit")
            .anyRequest().authenticated())
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .oauth2ResourceServer(o -> o.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
        .httpBasic(Customizer.withDefaults())
        .build();
  }

  public class MultipleHttpMethods implements RequestMatcher {

    private List<HttpMethod> httpMethods;
    private String path;
    private final AntPathMatcher antPathMatcher;

    MultipleHttpMethods(List<HttpMethod> httpMethods, String path) {
      this.httpMethods = httpMethods;
      this.path = path;
      this.antPathMatcher = new AntPathMatcher();
    }

    @Override
    public boolean matches(HttpServletRequest request) {

      String requestedPath = request.getRequestURI();
      String requestedHttpMethod = request.getMethod();

      return this.antPathMatcher.match(this.path, requestedPath)
          && this.httpMethods.stream().filter(m -> m.matches(requestedHttpMethod)).toArray().length > 0;

    }
  }

}
