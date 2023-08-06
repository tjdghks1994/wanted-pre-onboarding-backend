package com.wanted.repository;

import com.wanted.domain.Board;
import com.wanted.domain.BoardLookupInfo;
import com.wanted.domain.BoardViewInfo;
import com.wanted.domain.PageMakeVO;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    // 게시글 등록
    void save(Board board, Long memberNo);
    // 게시글 조회
    Optional<BoardLookupInfo> findById(Long boardId);
    // 게시글 목록
    List<BoardViewInfo> findAll(PageMakeVO pageMakeVO);
    // 게시글 전체 수
    int total();
    // 게시글 조회 수 증가
    void updateLookupCnt(Long boardId);
    // 게시글 삭제
    void delete(Long boardId);
    // 게시글 수정
    void update(Board board);
}
