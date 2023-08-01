package com.example.demo.api.auth.controller;

import com.example.demo.api.auth.dto.RequestRegister;
import com.example.demo.api.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Tag(name = "Auth", description = "회원가입")
    public ResponseEntity<?> register (@RequestBody @Valid RequestRegister requestRegister) {
        authService.register(requestRegister);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
