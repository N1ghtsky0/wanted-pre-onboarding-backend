package com.example.demo.api.board.service;

import com.example.demo.api.board.dao.getAllBoardMapping;
import com.example.demo.api.board.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    ResponseAddBoard addBoard(RequestAddBoard requestAddBoard);

    Page<getAllBoardMapping> getAllBoard(Pageable pageable);

    ResponseGetBoard getBoard(int id);

    void updateBoard(RequestUpdateBoard requestUpdateBoard);

    void deleteBoard(RequestDeleteBoard requestDeleteBoard);

}
