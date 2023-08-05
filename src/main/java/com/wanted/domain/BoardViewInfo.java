package com.wanted.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class BoardViewInfo {
    private Long boardNum;  // 게시글 번호
    private Long boardId;   // 게시글 ID
    private String boardTitle;  // 게시글 제목
    private LocalDate modifyDate;   // 게시글 수정일
    private Long lookupCnt; // 조회 수
    private String memberId;    // 게시글 작성자

    public BoardViewInfo() { }
}
