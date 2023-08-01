package com.example.demo.api.board.controller;

import com.example.demo.api.board.dto.RequestAddBoard;
import com.example.demo.api.board.dto.ResponseAddBoard;
import com.example.demo.api.board.service.BoardService;
import com.example.demo.global.annotation.UserAuthorize;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@UserAuthorize
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<ResponseAddBoard> addBoard(@RequestBody @Valid RequestAddBoard requestAddBoard) {
        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.addBoard(requestAddBoard));
    }

}
