package com.stc.appointmentmanagement.base.handler;

import com.stc.appointmentmanagement.base.exception.InvalidInputsException;
import com.stc.appointmentmanagement.base.response.ErrorResponse;
import com.stc.appointmentmanagement.base.exception.AppointmentConflictsException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NoSuchElementException.class})
    public ErrorResponse handleNoSuchElementException(NoSuchElementException e) {
        return ErrorResponse.badRequest(e.getMessage());
    }

//    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AppointmentConflictsException.class})
    public ErrorResponse handleDevicesExceededMaxException(AppointmentConflictsException e) {
        return ErrorResponse.internalServerError(e.getMessage());
    }

    @ExceptionHandler({InvalidInputsException.class})
    public ErrorResponse handleDevicesExceededMaxException(InvalidInputsException e) {
        return ErrorResponse.internalServerError(e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, UnexpectedTypeException.class})
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
