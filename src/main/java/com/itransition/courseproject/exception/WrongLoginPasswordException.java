package com.itransition.courseproject.exception;

import org.springframework.http.HttpStatus;

public class WrongLoginPasswordException extends GlobalException {
    public WrongLoginPasswordException(String message) {
        super(message, HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
}
