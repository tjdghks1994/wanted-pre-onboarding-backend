function modifyBoard() {
    let boardId = document.getElementById('board-info-section').getAttribute('bid');
    let boardTitle = document.getElementById('boardName').value;
    let boardContents = $('#summernote').summernote('code');   // 썸머노트 editor 값 가져오기

    let boardChangeInfo = {
        "boardId": boardId,
        "boardTitle": boardTitle,
        "boardContents": boardContents,
        "writerId": writerId
    };

    $.ajax({
        url: "/board",
        method: "PATCH",
        contentType: "application/json",
        data: JSON.stringify(boardChangeInfo),
        success: function (result, statusText, jqXHR) {
            alert(result);
            // 게시글 수정 완료하여 게시글 조회 페이지로 이동
            window.location.replace('/board/lookup?boardId=' +boardId);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.responseText != null) {
                alert(jqXHR.responseText);
            } else {
                alert('게시글 수정에 실패하였습니다. 관리자에게 문의하세요.');
            }
        }
    });
}

function cancelModify() {
    let isCancel = confirm('정말 게시글 수정을 취소하시겠습니까?');
    if (isCancel) {
        let boardId = document.getElementById('board-info-section').getAttribute('bid');
        // 게시글 조회 페이지로
        window.location.href = '/board/lookup?boardId=' + boardId;
    }
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
}

$(function () {
    initEditor();
});