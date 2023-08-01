package com.example.demo.global.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final String errorMessage;
    private final HttpStatus errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorMessage = errorCode.getMessage();
        this.errorCode = errorCode.getHttpStatus();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }
}
