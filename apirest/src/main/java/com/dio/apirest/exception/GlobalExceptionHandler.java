package com.dio.apirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is a global exception handler for the API REST application.
 * It handles different types of exceptions and returns appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles NotFoundException exceptions and returns a 404 Not Found response.
     * 
     * @param ex the NotFoundException instance that was thrown
     * @return a ResponseEntity containing the exception message and a 404 Not Found status
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles BadRequestException exceptions and returns a 400 Bad Request response.
     * 
     * @param ex the BadRequestException instance that was thrown
     * @return a ResponseEntity containing the exception message and a 400 Bad Request status
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic Exception instances and returns a 500 Internal Server Error response.
     * 
     * @param ex the Exception instance that was thrown
     * @return a ResponseEntity containing the exception message and a 500 Internal Server Error status
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleInternalServerErrorException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}