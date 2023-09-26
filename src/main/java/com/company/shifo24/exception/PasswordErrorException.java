package com.company.shifo24.exception;

public class PasswordErrorException extends RuntimeException{

    public PasswordErrorException(String message) {
        super(message);
    }
}