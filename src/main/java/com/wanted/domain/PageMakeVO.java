package com.wanted.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageMakeVO {
    private int startPage;  // 시작 페이지
    private int endPage;    // 끝 페이지
    private int prevPage;   // 이전 페이지
    private int nextPage;   // 다음 페이지
    private int firstPage;  // 첫 페이지 = 1
    private int lastPage;   // 맨 마지막 페이지
    private int total;  // 전체 게시물 수
    private PageCriteria pageCriteria;  // 현재 페이지 번호와 페이지당 보여줄 개수 정보를 가지고 있는 클래스

    public PageMakeVO(PageCriteria pageCriteria, int total) {
        this.pageCriteria = pageCriteria;
        this.total = total;

        // 마지막 페이지
        this.endPage = (int)(Math.ceil(pageCriteria.getPageNum()/10.0))*5;
        // 시작 페이지
        this.startPage = this.endPage - 4;
        // 총 게시글의 마지막 페이지
        int realEndPage = (int)(Math.ceil(total * 1.0/pageCriteria.getAmount()));
        // 총 게시글의 마지막 페이지가 현재 마지막 페이지의 값보다 작으면 마지막 페이지의 값을 총 게시글의 마지막 페이지로 변경
        if (realEndPage < endPage) {
            this.endPage = realEndPage;
        }
        // 이전 페이지는 현재 페이지의 -1의 값이 1보다 작으면 1로 초기화
        // 아니면 현재 페이지의 -1의 값으로 초기화
        this.prevPage = (pageCriteria.getPageNum() - 1) >= 1 ? pageCriteria.getPageNum() - 1 : 1;
        // 다음 페이지는 현재 페이지의 +1의 값이 총 게시글의 마지막 페이지보다 크면 총 게시글의 마지막 페이지로 초기화
        // 아니면 현재 페이지의 +1의 값으로 초기화
        this.nextPage = (pageCriteria.getPageNum() + 1) > realEndPage ? realEndPage : pageCriteria.getPageNum() + 1;
        // 맨 첫페이지
        this.firstPage = 1;
        // 맨 마지막페이지
        this.lastPage = realEndPage;
    }
}
