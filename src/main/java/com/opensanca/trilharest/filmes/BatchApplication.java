package com.opensanca.trilharest.filmes;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableAutoConfiguration
@ComponentScan
@EnableWebSecurity
@EnableScheduling
public class BatchApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BatchApplication.class)
            .web(false)
            .run(args);
    }

}
