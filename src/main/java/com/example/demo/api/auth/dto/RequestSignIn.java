package com.example.demo.api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSignIn {

    @NotBlank
    private String signInId;

    @NotBlank
    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
    private String signInPwd;

    public boolean checkEmailFormat() {
        return signInId.contains("@");
    }

}
