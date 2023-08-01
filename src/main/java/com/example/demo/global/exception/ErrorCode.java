package com.example.demo.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // auth
    BAD_REQUEST_EMAIL_FORMAT("이메일 형식이 잘못되었습니다.", BAD_REQUEST),
    BAD_REQUEST_SIGN_IN_ID_DUPLICATION("이미 회원가입된 이메일입니다.", BAD_REQUEST),

    // filter
    EXPIRE_TOKEN("이미 만료된 토큰입니다.", FORBIDDEN),
    INVALID_TOKEN("유효하지 않은 토큰입니다.", UNAUTHORIZED),
    ;

    private final String message;
    private final HttpStatus httpStatus;
}
