package com.wanted.service;

import com.wanted.domain.Board;
import com.wanted.domain.BoardAdd;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class BoardServiceImplTest {

    private final BoardService boardService;
    @Autowired
    public BoardServiceImplTest(BoardService boardService) {
        this.boardService = boardService;
    }

    @Test
    @DisplayName("게시글 등록 테스트")
    void add() {
        // given
        BoardAdd boardAdd = new BoardAdd();
        boardAdd.setBoardTitle("테스트 게시글 제목");
        boardAdd.setBoardContents("<h1> 게시글 등록 테스트 내용 작성 </h1>");
        String memberId = "parktjdghks@naver.com";  // 등록되어 있는 사용자
        String noMemberId = "tasdfasdf@naver.com";  // 등록되지 않은 사용자
        // when
        Long addBoardId = boardService.add(boardAdd, memberId);
        Optional<Board> findBoard = boardService.findBoard(addBoardId);
        // then
        Assertions.assertThatThrownBy(() -> boardService.add(boardAdd, noMemberId)).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThat(findBoard.get().getBoardId()).isEqualTo(addBoardId);
    }
}