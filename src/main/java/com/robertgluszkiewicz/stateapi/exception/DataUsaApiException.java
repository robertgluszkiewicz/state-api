package com.robertgluszkiewicz.stateapi.exception;

public class DataUsaApiException extends RuntimeException {
    public DataUsaApiException() {
        super("Could not processed data");
    }
}
