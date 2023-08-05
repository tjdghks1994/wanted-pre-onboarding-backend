package com.wanted.service;

import com.wanted.domain.*;
import com.wanted.repository.BoardRepository;
import com.wanted.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long add(BoardAdd boardAdd, String memberId) {
        // 사용자 정보 조회 ( 사용자의 NO 값을 알아오기 위한 )
        Optional<Member> findMemberInfo = memberRepository.findById(memberId);
        // 사용자 ID를 통해 사용자 정보를 조회하였는데 조회된 정보가 없다면 오류!
        if (!findMemberInfo.isPresent()) {
            throw new IllegalArgumentException(memberId + " 는 존재하지 않는 사용자입니다.");
        }
        Board board = new Board();
        board.setBoardTitle(boardAdd.getBoardTitle());
        board.setBoardContents(boardAdd.getBoardContents());
        // 조회한 사용자 정보와 함께 게시글 등록
        boardRepository.save(board, findMemberInfo.get().getMemberNo());

        return board.getBoardId();
    }
    @Override
    public Optional<Board> findBoard(Long boardId) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        if (!findBoard.isPresent()) {
            throw new IllegalArgumentException(boardId + " 는 존재하지 않는 게시글입니다.");
        }

        return findBoard;
    }
    @Override
    public List<BoardViewInfo> findAllBoard(PageMakeVO pageMakeVO) {
        return boardRepository.findAll(pageMakeVO);
    }
    @Override
    public int getTotalBoardCnt() {
        return boardRepository.total();
    }
}
