package com.vitalManager.vitalManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class NotTypeMedicoException extends  RuntimeException {
    public NotTypeMedicoException(String message) {
        super(message);
    }
}
