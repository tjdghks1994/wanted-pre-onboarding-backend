package com.wanted.repository;

import com.wanted.domain.Board;
import com.wanted.domain.BoardLookupInfo;
import com.wanted.domain.BoardViewInfo;
import com.wanted.domain.PageMakeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public Optional<BoardLookupInfo> findById(Long boardId) {
        return boardMapper.findById(boardId);
    }

    @Override
    public List<BoardViewInfo> findAll(PageMakeVO pageMakeVO) {
        return boardMapper.findAll(pageMakeVO);
    }
    @Override
    public int total() {
        return boardMapper.total();
    }

    @Override
    public void updateLookupCnt(Long boardId) {
        boardMapper.updateLookupCnt(boardId);
    }

    @Override
    public void delete(Long boardId) {
        boardMapper.delete(boardId);
    }
}
