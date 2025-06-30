package com.example.springboot_boilerplate_api.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.springboot_boilerplate_api.common.dto.ApiResponse;

/**
 * Global exception handler for consistent error responses across the application.
 *
 * <p>This class handles exceptions thrown by any controller and converts them into standardized API
 * responses. Add more specific exception handlers as needed.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /** Handle general exceptions */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobalException(Exception ex) {
        logger.error("Unexpected error occurred: ", ex);

        ApiResponse<Object> response = ApiResponse.error("An unexpected error occurred");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /** Handle illegal argument exceptions */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(
            IllegalArgumentException ex) {
        logger.error("Bad request: {}", ex.getMessage());

        ApiResponse<Object> response = ApiResponse.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // TODO: Add more specific exception handlers as your application grows
    // Example:
    // @ExceptionHandler(ResourceNotFoundException.class)
    // @ExceptionHandler(ValidationException.class)
    // @ExceptionHandler(AuthenticationException.class)
}
