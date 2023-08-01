package com.example.demo.api.auth.service;

import com.example.demo.api.auth.dto.RequestRegister;
import com.example.demo.api.auth.dto.RequestSignIn;
import com.example.demo.api.auth.dto.ResponseSignIn;
import com.example.demo.api.user.model.User;
import com.example.demo.api.user.repository.UserRepo;
import com.example.demo.global.config.jwt.TokenProvider;
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
    private final TokenProvider tokenProvider;

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
                .nickName(requestRegister.getNickName())
                .build());

    }

    @Override
    public ResponseSignIn signIn(RequestSignIn requestSignIn) {
        User user = userRepo.findBySignInId(requestSignIn.getSignInId())
                .filter(info -> passwordEncoder.matches(requestSignIn.getSignInPwd(), info.getSignInPwd()))
                .orElseThrow(() -> new BusinessException(NOT_FOUND_USER_INFO));

        String accessToken = tokenProvider.createToken(String.format("%s:%s", user.getNickName(), "USER"));

        return ResponseSignIn.builder()
                .nickName(user.getNickName())
                .accessToken(accessToken)
                .build();
    }
}
