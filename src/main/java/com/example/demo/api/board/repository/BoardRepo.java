package com.example.demo.api.board.repository;

import com.example.demo.api.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<Board, Integer> {
}
