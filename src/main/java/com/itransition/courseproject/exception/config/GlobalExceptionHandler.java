package com.itransition.courseproject.exception.config;

import com.itransition.courseproject.dto.response.ErrorResponse;
import com.itransition.courseproject.exception.GlobalException;
import com.itransition.courseproject.exception.UserExistException;
import com.itransition.courseproject.exception.WrongLoginPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<Object> handleGlobalException(GlobalException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorResponse(
                            ex.getStatus(),
                            ex.getCode(),
                            messageSource.getMessage(ex.getMessage(), null, Locale.ENGLISH)
                        )
                );
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<Object> handleUserExistGlobalException() {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        messageSource.getMessage("User already exist", null, Locale.ENGLISH)
                        )
                );
    }

    @ExceptionHandler(WrongLoginPasswordException.class)
    public ResponseEntity<Object> handleWrongLoginPasswordException() {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                messageSource.getMessage("Wrong login or password", null, Locale.ENGLISH)
                        )
                );
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(final UsernameNotFoundException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(
                        405,"Info","User not found"
                        )
                );
    }

}
