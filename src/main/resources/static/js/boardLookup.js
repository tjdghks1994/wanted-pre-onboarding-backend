function goBoardListPage() {
    // 게시글 목록 페이지로 이동
    window.location.href = '/board/list';
}

/**
 * 에디터 초기화
 */
function initEditor() {
    let fontList = ['Pretendard'];
    $('#summernote').summernote({
        height: 500,
        fontNames: fontList,
        fontNamesIgnoreCheck: fontList,
        fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
        toolbar: [
            // [groupName, [list of button]]
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
            ['color', ['forecolor','color']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert',['picture','link']]
        ]
    });
    // DB에 저장된 게시글 내용을 에디터에 표시
    $('#summernote').summernote('code', boardContents);
    // 서머노트 쓰기 비활성화
    $('#summernote').summernote('disable');
}

$(function () {
    initEditor();
});