package com.example.demo.api.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseSignIn {

    private String uid;
    private String accessToken;

}
