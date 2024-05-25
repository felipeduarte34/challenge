package com.agnes.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ChallengeApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ChallengeApplication.class, args);
    }
}
