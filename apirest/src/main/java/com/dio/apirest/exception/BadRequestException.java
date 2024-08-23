/**
 * This file contains the definition of the BadRequestException class.
 * 
 * The BadRequestException class is a custom exception that is thrown when a bad request is made in the API.
 * It extends the RuntimeException class and provides a constructor to set the exception message.
 * 
 * Example usage:
 * 
 *     throw new BadRequestException("Invalid request parameters");
 */
package com.dio.apirest.exception;

/**
 * Custom exception class for handling bad requests in the API.
 * 
 * This exception is thrown when a request is made with invalid parameters or data.
 * It extends the RuntimeException class, allowing it to be thrown without being declared.
 * 
 * @author Pedro Solozabal
 * @version 1.0
 */
public class BadRequestException extends RuntimeException {
    /**
     * Constructs a new BadRequestException with the specified detail message.
     * 
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public BadRequestException(String message) {
        super(message);
    }
}