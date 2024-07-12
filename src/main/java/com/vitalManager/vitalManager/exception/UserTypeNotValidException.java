package com.vitalManager.vitalManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserTypeNotValidException extends  RuntimeException {
    public UserTypeNotValidException(String message) {
        super(message);
    }
}
