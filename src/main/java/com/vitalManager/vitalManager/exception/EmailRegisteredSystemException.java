package com.vitalManager.vitalManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EmailRegisteredSystemException extends RuntimeException{
    public EmailRegisteredSystemException(String message) {
        super(message);
    }
}
