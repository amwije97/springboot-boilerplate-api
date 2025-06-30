package com.example.springboot_boilerplate_api.common.constants;

/**
 * Application-wide constants.
 *
 * <p>Centralize all constant values here to maintain consistency across the application. Group
 * related constants together and use descriptive names.
 */
public final class AppConstants {

    // API Configuration
    public static final String API_V1 = "/api/v1";

    // Default values for pagination (when you implement it)
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final int MAX_PAGE_SIZE = 100;

    // Default sorting
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    // TODO: Add more constants as your application grows
    // Examples:
    // - Security related constants (JWT expiration, roles, etc.)
    // - Business logic constants (status values, limits, etc.)
    // - External service configurations

    private AppConstants() {
        // Private constructor to prevent instantiation
        throw new UnsupportedOperationException(
                "This is a utility class and cannot be instantiated");
    }
}
