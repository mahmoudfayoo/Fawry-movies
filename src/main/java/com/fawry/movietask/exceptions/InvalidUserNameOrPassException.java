package com.fawry.movietask.exceptions;

public class InvalidUserNameOrPassException extends RuntimeException {
    public InvalidUserNameOrPassException(String message) {
        super(message);
    }
}
