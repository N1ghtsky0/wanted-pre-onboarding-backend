package com.example.demo.api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRegister {

    @NotBlank
    private String signInId;

    @NotBlank
    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
    private String signInPwd;

    @NotNull
    private String nickName;

    public boolean checkEmailFormat() {
        return signInId.contains("@");
    }

    // 닉네임을 따로 설정하지 않았을 경우 기본 닉네임 사용
    // 기본 닉네임: 이메일에서 "@" 앞 문자열
    public String getNickName() {
        if (nickName.isBlank()) {
            return signInId.split("@")[0];
        } else {
            return nickName;
        }
    }

}
