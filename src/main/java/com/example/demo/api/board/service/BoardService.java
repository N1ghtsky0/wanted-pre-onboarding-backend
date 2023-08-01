package com.example.demo.api.board.service;

import com.example.demo.api.board.dto.RequestAddBoard;
import com.example.demo.api.board.dto.ResponseAddBoard;

public interface BoardService {

    ResponseAddBoard addBoard(RequestAddBoard requestAddBoard);

}
