package com.wanted.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/addForm")
    public String addForm() {   // 게시글 등록 페이지
        return "board/addForm";
    }
}
