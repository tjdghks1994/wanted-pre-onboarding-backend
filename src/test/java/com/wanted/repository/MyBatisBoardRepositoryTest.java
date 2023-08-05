package com.wanted.repository;

import com.wanted.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class MyBatisBoardRepositoryTest {

    private final BoardRepository boardRepository;

    @Autowired
    public MyBatisBoardRepositoryTest(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Test
    @DisplayName("게시글 등록/조회 테스트")
    void save() {
        // given
        Board board = new Board();
        board.setBoardTitle("테스트 제목");
        board.setBoardContents("<h1>테스트 게시글 내용</h1>");
        Long memberNo = 11L;
        // when
        boardRepository.save(board, memberNo);
        Optional<BoardLookupInfo> findBoardLookup = boardRepository.findById(board.getBoardId());
        Board findBoard = new Board();
        findBoard.setBoardId(findBoardLookup.get().getBoardId());
        // then
        Assertions.assertThat(findBoardLookup).isPresent();
        Assertions.assertThat(findBoard).isEqualTo(board);
    }

    @Test
    @DisplayName("게시글 목록 조회 테스트")
    void list() {
        // given
        int totalBoardCnt = boardRepository.total();
        PageCriteria pageCriteria = new PageCriteria(1, 20);
        PageMakeVO pageMakeVO = new PageMakeVO(pageCriteria, totalBoardCnt);
        // when
        List<BoardViewInfo> allBoard = boardRepository.findAll(pageMakeVO);
//        allBoard.stream().forEach(boardViewInfo -> log.debug("BoardViewInfo = {}", boardViewInfo));
        // then
        Assertions.assertThat(allBoard.size()).isEqualTo(totalBoardCnt);
    }

    @Test
    @DisplayName("게시글 조회 수 증가 테스트")
    void updateLookupCnt() {
        // given
        Long boardId = 12L;
        BoardLookupInfo updateBoard = boardRepository.findById(boardId).get();
        Long updateBoardLookupCnt = updateBoard.getLookupCnt();
        // when
        boardRepository.updateLookupCnt(boardId);
        BoardLookupInfo findBoard = boardRepository.findById(boardId).get();
        // then
        Assertions.assertThat(updateBoardLookupCnt + 1).isEqualTo(findBoard.getLookupCnt());
    }
}