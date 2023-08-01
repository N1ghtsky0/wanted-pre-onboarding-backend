package com.example.demo.api.board.dao;

import java.time.LocalDateTime;

public interface getAllBoardMapping {

    Integer getId();
    String getTitle();
    String getContent();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    String getAuthor();

}
