package com.itransition.courseproject.exception;

import org.springframework.http.HttpStatus;

public class UserExistException extends GlobalException {
    public UserExistException(String message) {
        super(message, HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
}
