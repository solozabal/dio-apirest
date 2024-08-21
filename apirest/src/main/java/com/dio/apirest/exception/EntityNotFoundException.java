/**
 * This file contains the definition of the EntityNotFoundException class.
 * The EntityNotFoundException class is used to represent an exception that occurs when an entity is not found.
 * It is typically thrown when a requested entity cannot be found in the system.
 * This exception is part of the com.dio.apirest.exception package.
 */
package com.dio.apirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma entidade não é encontrada no banco de dados.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
