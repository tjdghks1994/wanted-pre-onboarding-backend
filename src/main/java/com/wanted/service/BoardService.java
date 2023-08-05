package com.wanted.service;

import com.wanted.domain.*;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    // 게시글 등록
    Long add(BoardAdd boardAdd, String memberId);
    // 게시글 조회
    BoardLookupInfo findBoard(String boardId);
    // 게시글 조회 수 증가
    void plusLookupCount(String boardId);
    // 게시글 목록
    List<BoardViewInfo> findAllBoard(PageMakeVO pageMakeVO);
    // 게시글 전체 수
    int getTotalBoardCnt();
    // 게시글 삭제
    void removeBoard(String boardId);
    // 게시글 수정
    void changeBoard(BoardChangeInfo boardChangeInfo);
}
