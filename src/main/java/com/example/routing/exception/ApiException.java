package com.example.routing.exception;

/**
 * Application-level runtime exception with an error code.
 * Provides structured information to API clients while keeping stack traces
 * out of generic responses.
 */
public class ApiException extends RuntimeException {

    private final String code;

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
