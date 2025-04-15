package com.fawry.movietask.exceptionhandler;

import com.fawry.movietask.exceptions.DoublicateUserException;
import com.fawry.movietask.exceptions.InvalidUserNameOrPassException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidUserNameOrPassException.class)
    public ResponseEntity<String> handleRuntimeException(InvalidUserNameOrPassException ex) {
        return new ResponseEntity<>("Auth Exception" + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DoublicateUserException.class)
    public ResponseEntity<String> handleRuntimeException(DoublicateUserException ex) {
        return new ResponseEntity<>("Auth Exception" + ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
