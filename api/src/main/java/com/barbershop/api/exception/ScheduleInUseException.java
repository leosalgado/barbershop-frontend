package com.barbershop.api.exception;

public class ScheduleInUseException extends RuntimeException {
    public ScheduleInUseException(String message) {
        super(message);
    }
}
