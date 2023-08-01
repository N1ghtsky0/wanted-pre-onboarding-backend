package com.example.demo.api.board.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAddBoard {

    @NotNull(message = "제목은 빈칸일 수 없습니다.")
    private String title;

    @NotNull(message = "내용은 빈칸일 수 없습니다.")
    private String content;

    @NotNull(message = "작성자 정보는 빈칸일 수 없습니다.")
    private String uid;

}
