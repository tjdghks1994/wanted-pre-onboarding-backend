package com.wanted.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardRemoveInfo {
    private String boardId;     // 게시글 ID
    private String writerId;    // 작성자 ID

    public BoardRemoveInfo() { }
}
