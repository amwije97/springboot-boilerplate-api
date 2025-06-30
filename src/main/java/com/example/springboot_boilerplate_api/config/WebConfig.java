package com.example.springboot_boilerplate_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration for the application.
 *
 * <p>Configure CORS, interceptors, formatters, and other web-related settings here. This is where
 * you customize Spring MVC behavior.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure CORS mappings.
     *
     * <p>TODO: Customize CORS settings based on your requirements. In production, be more
     * restrictive with allowed origins.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://localhost:8080") // TODO: Configure for your frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    // TODO: Add more web configurations as needed
    // Examples:
    // - Custom interceptors
    // - Message converters
    // - View resolvers
    // - Resource handlers for static content
}
