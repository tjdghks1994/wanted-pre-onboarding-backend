package com.wanted.repository;

import com.wanted.domain.Board;
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
        Optional<Board> findBoard = boardRepository.findById(board.getBoardId());
        // then
        Assertions.assertThat(findBoard).isPresent();
        Assertions.assertThat(findBoard.get()).isEqualTo(board);
    }
}