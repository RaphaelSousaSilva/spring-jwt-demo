package br.com.raph.demo.exception;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;

public class CustomEntityNotFoundException extends EntityNotFoundException {
    private static final String ERROR_MESSAGE = "Entity not found";
    public CustomEntityNotFoundException() {
        super(ERROR_MESSAGE);
    }
}
