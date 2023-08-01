package com.example.demo.api.board.service;

import com.example.demo.api.board.dao.getAllBoardMapping;
import com.example.demo.api.board.dto.RequestAddBoard;
import com.example.demo.api.board.dto.ResponseAddBoard;
import com.example.demo.api.board.model.Board;
import com.example.demo.api.board.repository.BoardRepo;
import com.example.demo.api.user.repository.UserRepo;
import com.example.demo.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.demo.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepo boardRepo;
    private final UserRepo userRepo;

    @Override
    public ResponseAddBoard addBoard(RequestAddBoard requestAddBoard) {
        Board board = boardRepo.save(Board.builder()
                .title(requestAddBoard.getTitle())
                .content(requestAddBoard.getContent())
                .user(userRepo.findByUuid(requestAddBoard.getUid()).orElseThrow(
                        () -> new BusinessException(NOT_FOUND_USER)
                ))
                .build());
        return ResponseAddBoard.builder().boardId(board.getId()).build();
    }

    @Override
    public Page<getAllBoardMapping> getAllBoard(Pageable pageable) {
        return boardRepo.selectAllBoard(pageable);
    }

}