package com.barbershop.api.exception;

public class PhoneInUseException extends RuntimeException {
    public PhoneInUseException(String message) {
        super(message);
    }
}
