package com.wanted.controller;

import com.wanted.domain.BoardLookupInfo;
import com.wanted.domain.BoardViewInfo;
import com.wanted.domain.PageCriteria;
import com.wanted.domain.PageMakeVO;
import com.wanted.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/addForm")
    public String addForm() {   // 게시글 등록 페이지
        return "board/addForm";
    }

    @GetMapping("/list")
    public String boardList(@ModelAttribute PageCriteria pageCriteria, Model model) {
        log.debug("pageCriteria = {}", pageCriteria);
        if (pageCriteria == null) {
            // 첫번째 페이지
            pageCriteria = new PageCriteria();
        }

        // 전체 게시글 수
        int totalBoardCnt = boardService.getTotalBoardCnt();
        PageMakeVO pageMakeVO = new PageMakeVO(pageCriteria, totalBoardCnt);
        List<BoardViewInfo> boardList = boardService.findAllBoard(pageMakeVO);
        log.debug("boardList = {}", boardList);

        model.addAttribute("pageMake", pageMakeVO);
        model.addAttribute("boardList", boardList);

        return "board/list";
    }
    @GetMapping("/lookup")
    public String lookupBoard(@RequestParam String boardId, Authentication authentication, Model model) {
        BoardLookupInfo boardInfo = boardService.findBoard(boardId);
        model.addAttribute("boardInfo", boardInfo);
        model.addAttribute("username", authentication.getName());

        return "board/lookup";
    }

}
