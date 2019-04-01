package com.example.demo.core;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String message;

    private ErrorResponse() {
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public static ErrorResponse getInstance(String message) {
        return new ErrorResponse(message);
    }
}
