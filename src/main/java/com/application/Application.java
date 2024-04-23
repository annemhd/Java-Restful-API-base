package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  // @Configuration
  // public class CorsConfig implements WebMvcConfigurer {

  // @Override
  // public void addCorsMappings(CorsRegistry registry) {
  // registry.addMapping("/**")
  // .allowedOrigins("http://localhost:3000/") // Replace with your frontend
  // domain
  // .allowedMethods("GET", "POST", "PUT", "DELETE")
  // .allowedHeaders("*");
  // }
  // }
}