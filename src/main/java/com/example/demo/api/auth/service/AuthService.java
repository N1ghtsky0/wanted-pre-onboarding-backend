package com.example.demo.api.auth.service;

import com.example.demo.api.auth.dto.RequestRegister;
import com.example.demo.api.auth.dto.RequestSignIn;
import com.example.demo.api.auth.dto.ResponseSignIn;

public interface AuthService {

    void register(RequestRegister requestRegister);

    ResponseSignIn signIn(RequestSignIn requestSignIn);
}
