package com.example.demo.api.board.service;

import com.example.demo.api.board.dao.getAllBoardMapping;
import com.example.demo.api.board.dto.*;
import com.example.demo.api.board.model.Board;
import com.example.demo.api.board.repository.BoardRepo;
import com.example.demo.api.user.repository.UserRepo;
import com.example.demo.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public ResponseGetBoard getBoard(int id) {
        Board board = boardRepo.findById(id).orElseThrow(() -> new BusinessException(NOT_FOUND_BOARD));
        return ResponseGetBoard.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .build();
    }

    @Override
    @Transactional
    public void updateBoard(RequestUpdateBoard requestUpdateBoard) {
        Board board = checkAuthorization(requestUpdateBoard.getBoardId(), requestUpdateBoard.getUserUuid());
        board.updateContent(requestUpdateBoard.getContent());
    }

    @Override
    public void deleteBoard(RequestDeleteBoard requestDeleteBoard) {
        Board board = checkAuthorization(requestDeleteBoard.getBoardId(), requestDeleteBoard.getUserUuid());
        boardRepo.delete(board);
    }

    private Board checkAuthorization(int boardId, String userUuid) {
        Board board = boardRepo.findById(boardId)
                .orElseThrow(() -> new BusinessException(NOT_FOUND_BOARD));

        if (!board.checkAuthor(userRepo.findByUuid(userUuid).orElseThrow(() -> new BusinessException(NOT_FOUND_USER)))) {
            throw new BusinessException(FORBIDDEN_AUTHOR);
        }

        return board;
    }

}
