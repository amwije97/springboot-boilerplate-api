package com.example.springboot_boilerplate_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBoilerplateApiApplication {

    /** Private constructor to prevent instantiation of this utility class. */
    private SpringbootBoilerplateApiApplication() {
        // Private constructor for utility class
    }

    public static void main(final String[] args) {
        SpringApplication.run(SpringbootBoilerplateApiApplication.class, args);
    }
}
