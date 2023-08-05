package com.wanted.repository;

import com.wanted.domain.Board;
import com.wanted.domain.BoardLookupInfo;
import com.wanted.domain.BoardViewInfo;
import com.wanted.domain.PageMakeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {

    // 게시글 등록
    void save(@Param("board") Board board, @Param("memberNo") Long memberNo);
    // 게시글 조회
    Optional<BoardLookupInfo> findById(Long boardId);
    // 게시글 목록
    List<BoardViewInfo> findAll(@Param("paging") PageMakeVO pageMakeVO);
    // 전체 게시글 수
    int total();
    // 게시글 조회 수 증가
    void updateLookupCnt(Long boardId);
}
