package com.wanted.service;

import com.wanted.domain.Board;
import com.wanted.domain.BoardAdd;

import java.util.Optional;

public interface BoardService {
    // 게시글 등록
    Long add(BoardAdd boardAdd, String memberId);
    // 게시글 조회
    Optional<Board> findBoard(Long boardId);
}
