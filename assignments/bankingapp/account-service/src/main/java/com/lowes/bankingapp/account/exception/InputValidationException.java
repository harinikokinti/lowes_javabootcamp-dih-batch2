package com.lowes.bankingapp.account.exception;

public class InputValidationException extends RuntimeException {
    public InputValidationException(String message) {
        super(message);
    }
}
