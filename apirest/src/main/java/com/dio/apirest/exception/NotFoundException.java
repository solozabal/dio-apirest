
/**
 * Represents an exception that is thrown when a resource is not found.
 * This exception is a subclass of the RuntimeException class.
 */
package com.dio.apirest.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}