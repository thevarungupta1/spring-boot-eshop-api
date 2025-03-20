package com.thevarungupta.eshop.rest.api.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AuthException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public AuthException(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
}
