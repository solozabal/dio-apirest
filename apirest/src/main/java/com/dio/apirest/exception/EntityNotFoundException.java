package com.dio.apirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This file contains the definition of the EntityNotFoundException class.
 * 
 * The EntityNotFoundException class is used to represent an exception that occurs when an entity is not found.
 * It is typically thrown when a requested entity cannot be found in the system.
 * This exception is part of the com.dio.apirest.exception package.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new EntityNotFoundException with the specified detail message.
     * 
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new EntityNotFoundException with the specified detail message and cause.
     * 
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause the cause (which is saved for later retrieval by the getCause() method)
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}