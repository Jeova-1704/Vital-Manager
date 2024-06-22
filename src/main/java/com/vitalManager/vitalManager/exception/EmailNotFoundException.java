package com.vitalManager.vitalManager.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException (String message) {
        super(message);
    }
}
