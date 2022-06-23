package com.bigshots.spabackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bigshots.spabackend.*;

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
	  
	  @Override
	  public void addFormatters(FormatterRegistry registry) {
	        registry.addConverter(new LongToJokeConverter());
	  }
}

