package com.stc.appointmentmanagement.base.exception;

public class InvalidInputsException extends RuntimeException {
    private String message;

    public InvalidInputsException(String message) {
        super(message);
        this.message = message;
    }

    public InvalidInputsException() {
    }
}

