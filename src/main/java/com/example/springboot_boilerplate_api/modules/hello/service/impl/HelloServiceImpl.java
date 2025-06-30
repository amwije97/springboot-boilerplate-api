package com.example.springboot_boilerplate_api.modules.hello.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.springboot_boilerplate_api.modules.hello.service.HelloService;

/**
 * Implementation of HelloService.
 *
 * <p>This demonstrates: - Service implementation pattern - Business logic encapsulation - Logging
 * best practices - Input validation and processing
 */
@Service
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String getPersonalizedGreeting(String name) {
        logger.debug("Generating personalized greeting for: {}", name);

        // Simple input validation
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        // Simple business logic
        String cleanName = name.trim();
        return String.format("Hello, %s! Welcome to our API!", cleanName);
    }

    // TODO: Implement more business methods as your application grows
    // Examples:
    // - Database operations (inject repositories)
    // - External API calls (inject HTTP clients)
    // - Complex business calculations
    // - Data transformations
}
