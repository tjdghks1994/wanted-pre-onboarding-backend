package com.wanted.repository;

import com.wanted.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface BoardMapper {

    // 게시글 등록
    void save(@Param("board") Board board, @Param("memberNo") Long memberNo);
    // 게시글 조회
    Optional<Board> findById(Long boardId);
}
