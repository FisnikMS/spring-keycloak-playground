package com.fk.flashcards.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fk.flashcards.controller.decorator.ExtractJwtResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    ExtractJwtResolver extractJwtResolver = new ExtractJwtResolver();
    argumentResolvers.add(extractJwtResolver);
  }
}
