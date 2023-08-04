package com.wanted.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardAdd {
    private String boardTitle;  // 게시글 제목
    private String boardContents;   // 게시글 내용

    public BoardAdd() { }
}
