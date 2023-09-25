package com.company.shifo24.exception;

public class ConfirmPasswordErrorException extends RuntimeException{

    public ConfirmPasswordErrorException(String message) {
        super(message);
    }
}