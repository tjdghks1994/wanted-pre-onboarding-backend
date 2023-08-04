package com.wanted.repository;

import com.wanted.domain.Board;

import java.util.Optional;

public interface BoardRepository {
    // 게시글 등록
    void save(Board board, Long memberNo);

    // 게시글 조회
    Optional<Board> findById(Long boardId);
}
