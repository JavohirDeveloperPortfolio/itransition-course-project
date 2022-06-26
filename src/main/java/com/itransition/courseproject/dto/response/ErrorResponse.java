package com.itransition.courseproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse{
    private int code;
    private String status;
    private String message;
    private Object error;

    public ErrorResponse(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(int code, String status, Object error) {
        this.code = code;
        this.status = status;
        this.error = error;
    }
}
