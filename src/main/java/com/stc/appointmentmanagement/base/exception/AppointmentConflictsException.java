package com.stc.appointmentmanagement.base.exception;

public class AppointmentConflictsException extends RuntimeException {
    private String message;

    public AppointmentConflictsException(String message) {
        super(message);
        this.message = message;
    }

    public AppointmentConflictsException() {
    }
}
