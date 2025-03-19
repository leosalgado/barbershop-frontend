package com.barbershop.api.exception;

public class EmailInUseException extends RuntimeException {
    public EmailInUseException(String message) {
        super(message);
    }
}
