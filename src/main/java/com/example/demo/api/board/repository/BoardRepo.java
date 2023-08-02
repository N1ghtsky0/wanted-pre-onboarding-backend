package com.example.demo.api.board.repository;

import com.example.demo.api.board.dao.getAllBoardMapping;
import com.example.demo.api.board.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepo extends JpaRepository<Board, Integer> {

    @Query(value = "select " +
            "B.id as id, " +
            "B.title as title, " +
            "B.content as content, " +
            "B.createdAt as createdAt, " +
            "B.updatedAt as updatedAt, " +
            "U.nickName as author " +
            "from Board as B " +
            "join User U on B.user.id = U.id")
    Page<getAllBoardMapping> selectAllBoard(Pageable pageable);

}
