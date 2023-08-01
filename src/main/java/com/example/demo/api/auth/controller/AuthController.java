package com.example.demo.api.auth.controller;

import com.example.demo.api.auth.dto.RequestRegister;
import com.example.demo.api.auth.dto.RequestSignIn;
import com.example.demo.api.auth.dto.ResponseSignIn;
import com.example.demo.api.auth.service.AuthService;
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
    public ResponseEntity<?> register(@RequestBody @Valid RequestRegister requestRegister) {
        authService.register(requestRegister);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseSignIn> signIn(@RequestBody @Valid RequestSignIn requestSignIn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.signIn(requestSignIn));
    }

}
