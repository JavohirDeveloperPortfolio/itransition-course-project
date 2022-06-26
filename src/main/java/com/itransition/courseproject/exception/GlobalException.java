package com.itransition.courseproject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException{
    private String code;
    private int status;

    public GlobalException(String message, String code, int status){
        super(message);
        this.code = code;
        this.status = status;
    }
}
