package com.wanted.controller.api;

import com.wanted.domain.BoardAdd;
import com.wanted.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String add(@RequestBody BoardAdd boardAdd, Authentication authentication) {
        log.debug("{} 님이 게시글 등록을 요청합니다. boardAdd = {}", authentication.getName(), boardAdd);
        Long addBoardId = boardService.add(boardAdd, authentication.getName());

        return addBoardId + "번의 게시글 등록이 완료 되었습니다.";
    }
}
