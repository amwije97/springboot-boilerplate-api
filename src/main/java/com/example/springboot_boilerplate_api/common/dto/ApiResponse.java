package com.example.springboot_boilerplate_api.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Generic API response wrapper.
 *
 * @param <T> the type of data in the response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String error;

    private ApiResponse() {
        // Private constructor for builder pattern
    }

    /**
     * Creates a successful response with data.
     *
     * @param data the response data
     * @param message the success message
     * @param <T> the type of data
     * @return ApiResponse instance
     */
    public static <T> ApiResponse<T> success(final T data, final String message) {
        final ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        response.message = message;
        return response;
    }

    /**
     * Creates a successful response with data and default message.
     *
     * @param data the response data
     * @param <T> the type of data
     * @return ApiResponse instance
     */
    public static <T> ApiResponse<T> success(final T data) {
        return success(data, "Success");
    }

    /**
     * Creates an error response.
     *
     * @param errorMessage the error message
     * @param <T> the type of data
     * @return ApiResponse instance
     */
    public static <T> ApiResponse<T> error(final String errorMessage) {
        final ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.error = errorMessage;
        return response;
    }

    /**
     * Gets the success status.
     *
     * @return true if successful, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getError() {
        return error;
    }
}
