package com.example.demo.api.board.controller;

import com.example.demo.api.board.dao.getAllBoardMapping;
import com.example.demo.api.board.dto.RequestAddBoard;
import com.example.demo.api.board.dto.ResponseAddBoard;
import com.example.demo.api.board.dto.ResponseGetBoard;
import com.example.demo.api.board.service.BoardService;
import com.example.demo.global.annotation.AnonymousAuthorize;
import com.example.demo.global.annotation.UserAuthorize;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @AnonymousAuthorize
    public ResponseEntity<Page<getAllBoardMapping>> getAllBoard(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getAllBoard(pageable));
    }

    @GetMapping("/{id}")
    @AnonymousAuthorize
    public ResponseEntity<ResponseGetBoard> getBoard(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoard(id));
    }

}
