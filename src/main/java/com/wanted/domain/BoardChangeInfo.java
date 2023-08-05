package com.wanted.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardChangeInfo {
    private String boardId; // 게시글 ID
    private String boardTitle;  // 게시글 제목
    private String boardContents;   // 게시글 내용
    private String writerId;    // 게시글 작성자 ID

    public BoardChangeInfo() { }
}
