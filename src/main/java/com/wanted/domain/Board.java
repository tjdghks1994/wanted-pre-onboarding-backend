package com.wanted.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Board {
    private Long boardId;   // 게시글 ID
    private String boardTitle;  // 게시글 제목
    private String boardContents;   // 게시글 내용
    private LocalDate modifyDate;   // 게시글 수정일
    private Long lookupCnt; // 조회 수

    public Board() { }

    @Override
    public int hashCode() {
        return Objects.hash(this.boardId);
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Board)) {
            throw new IllegalArgumentException("Argument Type is Not Board");
        }
        Board board = (Board) obj;

        return this.boardId == board.getBoardId();
    }
}

