package com.example.demo.api.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateBoard {

    @NotBlank(message = "유저 정보는 빈칸일 수 없습니다.")
    private String userUuid;

    @NotNull(message = "게시글 아이디는 빈칸일 수 없습니다.")
    private Integer boardId;

    @NotBlank(message = "내용은 빈칸일 수 없습니다.")
    private String content;

}
