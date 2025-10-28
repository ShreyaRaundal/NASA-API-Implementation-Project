package com.openapi.nasa.exceptionHandler;

public class NasaNotFoundException extends RuntimeException {
    public NasaNotFoundException() {
    }

    public NasaNotFoundException(String message) {
        super(message);
    }

    public NasaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NasaNotFoundException(Throwable cause) {
        super(cause);
    }
}
