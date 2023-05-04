package com.robertgluszkiewicz.stateapi.exception;

public class DataUsaApiException extends RuntimeException {
    public DataUsaApiException() {
        super("No access to external resources");
    }
}
