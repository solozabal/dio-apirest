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

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}