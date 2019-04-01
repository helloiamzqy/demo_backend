package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    protected HttpStatus code = HttpStatus.BAD_REQUEST;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }
}
