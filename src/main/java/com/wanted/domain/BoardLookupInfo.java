package com.wanted.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BoardLookupInfo {
    private Long boardId;   // 게시글 ID
    private String boardTitle;  // 게시글 제목
    private String boardContents;   // 게시글 내용
    private LocalDate modifyDate;   // 게시글 수정일
    private Long lookupCnt; // 조회 수
    private String memberId;    // 게시글 작성자

    public BoardLookupInfo() { }
}
