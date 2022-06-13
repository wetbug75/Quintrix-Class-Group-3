package com.bigshots.spabackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer{
	  // this is enabled in WebSecurity Config under configure method .cor
	  @Override
	  public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**")
	        .allowedMethods("*")
	        .allowedHeaders("*")
	        .allowedOrigins("*") //.allowedOrigins("http://localhost:4200")
	        .allowCredentials(false) //this will be investigated later. 
	        .maxAge(-1);
	  }
}

