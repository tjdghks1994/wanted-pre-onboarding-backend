package com.wanted.controller.api;

import com.wanted.domain.*;
import com.wanted.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardApiController {
    /**
     * REST API
     * 게시글 조회   /board          GET
     * 게시글 목록   /board/boards   GET
     * 게시글 등록   /board          POST
     * 게시글 삭제   /board          DELETE
     * 게시글 수정   /board          PATCH
     */
    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String add(@RequestBody BoardAdd boardAdd, Authentication authentication) {
        log.debug("{} 님이 게시글 등록을 요청합니다. boardAdd = {}", authentication.getName(), boardAdd);
        Long addBoardId = boardService.add(boardAdd, authentication.getName());

        return "게시글 등록이 완료 되었습니다.";
    }

    @GetMapping("/boards")
    public List<BoardViewInfo> boardList(@RequestBody PageCriteria pageCriteria) {
        if (pageCriteria == null) {
            // 첫번째 페이지
            pageCriteria = new PageCriteria();
        }

        // 전체 게시글 수
        int totalBoardCnt = boardService.getTotalBoardCnt();
        PageMakeVO pageMakeVO = new PageMakeVO(pageCriteria, totalBoardCnt);
        List<BoardViewInfo> boardList = boardService.findAllBoard(pageMakeVO);

        return boardList;
    }
    @GetMapping
    public BoardLookupInfo lookupBoard(@RequestBody String boardId) {
        BoardLookupInfo boardInfo = boardService.findBoard(boardId);

        return boardInfo;
    }

    @DeleteMapping
    public String removeBoard(@RequestBody BoardRemoveInfo removeInfo, Authentication authentication) {
        String writerId = removeInfo.getWriterId(); // 게시글 작성자 ID
        String loginId = authentication.getName();  // 로그인한 ID
        String boardId = removeInfo.getBoardId();
        // 로그인 ID와 게시글 작성자 ID가 다른 경우 오류 (삭제 불가)
        if (!writerId.equals(loginId)) {
            throw new IllegalArgumentException("삭제에 실패했습니다. 해당 게시글은 " + writerId + " 님이 삭제할 수 있습니다.");
        }
        // 게시글 삭제
        boardService.removeBoard(boardId);

        return "게시글이 삭제 되었습니다.";
    }
}
