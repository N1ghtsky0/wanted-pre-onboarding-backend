package com.example.demo.api.auth.service;

import com.example.demo.api.auth.dto.RequestRegister;
import com.example.demo.api.user.model.User;
import com.example.demo.api.user.repository.UserRepo;
import com.example.demo.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.demo.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RequestRegister requestRegister) {

        if (!requestRegister.checkEmailFormat()) {
            throw new BusinessException(BAD_REQUEST_EMAIL_FORMAT);
        } else if (userRepo.existsBySignInId(requestRegister.getSignInId())) {
            throw new BusinessException(BAD_REQUEST_SIGN_IN_ID_DUPLICATION);
        }

        userRepo.save(User.builder()
                .signInId(requestRegister.getSignInId())
                .signInPwd(passwordEncoder.encode(requestRegister.getSignInPwd()))
                .uuid(UUID.randomUUID().toString())
                .build());

    }
}
