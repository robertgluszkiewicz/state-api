package com.robertgluszkiewicz.stateapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataUsaApiException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorMessage dataUsaApiExceptionHandler(DataUsaApiException ex) {
        return new ErrorMessage(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                ex.getMessage(),
                Instant.now().getEpochSecond()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage internalExceptionHandler(RuntimeException ex) {
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error",
                Instant.now().getEpochSecond()
        );
    }
}
