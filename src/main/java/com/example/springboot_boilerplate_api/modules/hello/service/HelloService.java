package com.example.springboot_boilerplate_api.modules.hello.service;

/**
 * Service interface for Hello module operations.
 *
 * <p>This demonstrates the service layer pattern: - Define business logic contracts in interfaces -
 * Keep controllers thin by delegating to services - Enable easy testing with mocks - Support
 * multiple implementations if needed
 */
public interface HelloService {

    /**
     * Get a personalized greeting message.
     *
     * @param name the name to include in the greeting
     * @return personalized greeting message
     */
    String getPersonalizedGreeting(String name);

    // TODO: Add more business method contracts as your application grows
    // Examples:
    // - Data validation methods
    // - Business rule enforcement
    // - Integration with external services
    // - Complex business calculations
}
