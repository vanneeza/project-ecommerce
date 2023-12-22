package com.ecommerce.app.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InsufficientException extends RuntimeException{
    public InsufficientException (String message) {
        super(message);
    }
}
