package com.example.demo.api.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDeleteBoard {

    @NotNull
    private Integer boardId;

    @NotBlank
    private String userUuid;

}
