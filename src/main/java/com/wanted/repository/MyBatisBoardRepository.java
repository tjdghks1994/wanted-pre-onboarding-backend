package com.wanted.repository;

import com.wanted.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MyBatisBoardRepository implements BoardRepository {
    private final BoardMapper boardMapper;
    @Autowired
    public MyBatisBoardRepository(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }
    @Override
    public void save(Board board, Long memberNo) {
        boardMapper.save(board, memberNo);
    }

    @Override
    public Optional<Board> findById(Long boardId) {
        return boardMapper.findById(boardId);
    }
}
