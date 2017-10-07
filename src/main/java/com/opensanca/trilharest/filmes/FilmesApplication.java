package com.opensanca.trilharest.filmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class FilmesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmesApplication.class, args);
    }

}
