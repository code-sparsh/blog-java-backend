package com.example.welcome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class welcomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(welcomeApplication.class, args);
    }

}