package com.wanted.service;

import com.wanted.domain.Board;
import com.wanted.domain.BoardAdd;
import com.wanted.domain.BoardChangeInfo;
import com.wanted.domain.BoardLookupInfo;
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
        BoardLookupInfo findBoard = boardService.findBoard(String.valueOf(addBoardId));
        // then
        Assertions.assertThatThrownBy(() -> boardService.add(boardAdd, noMemberId)).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThat(findBoard.getBoardId()).isEqualTo(addBoardId);
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void remove() {
        // given
        String boardId = "12";
        String loginId = "parktjdghks@naver.com";
        // when
        boardService.removeBoard(boardId, loginId);
        // then
        Assertions.assertThatThrownBy(() -> boardService.findBoard(boardId)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void change() {
        // given
        String loginID = "parktjdghks@naver.com";
        BoardChangeInfo boardChangeInfo = new BoardChangeInfo();
        boardChangeInfo.setBoardId("12");
        boardChangeInfo.setBoardTitle("게시글 수정 테스트~~~~");
        boardChangeInfo.setBoardContents("<p>게시글 수정 테스트 진행</p>");
        // when
        boardService.changeBoard(boardChangeInfo, loginID);
        BoardLookupInfo findBoard = boardService.findBoard(boardChangeInfo.getBoardId());
        // then
        Assertions.assertThat(boardChangeInfo.getBoardTitle()).isEqualTo(findBoard.getBoardTitle());
        Assertions.assertThat(boardChangeInfo.getBoardContents()).isEqualTo(findBoard.getBoardContents());
    }
}