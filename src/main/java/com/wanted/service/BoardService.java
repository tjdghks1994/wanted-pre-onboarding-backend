package com.wanted.service;

import com.wanted.domain.*;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    // 게시글 등록
    Long add(BoardAdd boardAdd, String memberId);
    // 게시글 조회
    BoardLookupInfo findBoard(String boardId);
    // 게시글 목록
    List<BoardViewInfo> findAllBoard(PageMakeVO pageMakeVO);
    // 게시글 전체 수
    int getTotalBoardCnt();
}
