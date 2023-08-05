function goBucketAddForm() {
    // 버킷 등록 페이지로 이동
    window.location.href = '/board/addForm';
}

/**
 * 페이지 버튼 클릭 시 페이지 이동
 * @param pageBtn : 클릭된페이징버튼
 */
function pageMove(pageBtn) {
    let pageVal = $(pageBtn).attr('value');

    window.location.href = '/board/list?pageNum=' + pageVal;
}