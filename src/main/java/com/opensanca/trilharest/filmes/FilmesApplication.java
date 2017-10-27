package com.opensanca.trilharest.filmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@ComponentScan
@EnableSwagger2
public class FilmesApplication {

  public static void main(String[] args) {
    SpringApplication.run(FilmesApplication.class, args);
  }
}
