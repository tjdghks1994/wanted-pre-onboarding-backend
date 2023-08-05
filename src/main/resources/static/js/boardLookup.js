function goBoardModifyPage() {
    let boardId = document.getElementById('board-info-tbl').getAttribute('bid');
    // 게시글 수정 페이지로 이동 처리
    window.location.href = '/board/modifyForm?boardId=' + boardId;
}

function removeBoard() {
    let isRemove = confirm('정말 게시글을 삭제하시겠습니까?');
    if (isRemove) {
        let boardId = document.getElementById('board-info-tbl').getAttribute('bid');
        let writerId = document.getElementById('board-writer').innerText;

        let removeInfo = {
            "boardId": boardId,
            "writerId": writerId
        };

        $.ajax({
            url: "/board",
            method: "delete",
            contentType: "application/json",
            data: JSON.stringify(removeInfo),
            success: function (result, statusText, jqXHR) {
                alert(result);
                // 게시글 삭제 완료하여 게시글 목록 페이지로 이동
                window.location.replace('/board/list');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.responseText != null) {
                    alert(jqXHR.responseText);
                } else {
                    alert('게시글 삭제에 실패하였습니다. 관리자에게 문의하세요.');
                }
            }
        });
    }
}

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