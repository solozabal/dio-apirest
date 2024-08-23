package com.dio.apirest.exception;

/**
 * Represents an exception that is thrown when a resource is not found.
 * This exception is a subclass of the RuntimeException class.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException with the specified detail message.
     * 
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public NotFoundException(String message) {
        super(message);
    }
}