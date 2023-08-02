package com.example.demo.api.board.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ResponseGetBoard {

    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

}
