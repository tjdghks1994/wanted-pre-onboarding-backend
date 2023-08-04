function addBoard() {
    let boardTitle = document.getElementById('boardName').value;
    let boardContents = $('#summernote').summernote('code');   // 썸머노트 editor 값 가져오기

    let boardAdd = {
        "boardTitle": boardTitle,
        "boardContents": boardContents
    }

    $.ajax({
        url: "/board",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(boardAdd),
        success: function (result, statusText, jqXHR) {
            alert(result);
            // 게시글 목록 페이지로 이동

        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.responseText != null) {
                alert(jqXHR.responseText);
            } else {
                alert('게시글 등록에 실패하였습니다. 관리자에게 문의하세요.');
            }
        }
    });
}

function cancelAdd() {
    let isCancel = confirm('취소 시, 게시글 목록 페이지로 이동합니다. 정말 등록을 취소하시겠습니까?');

    if (isCancel) {
        // 게시글 목록 페이지로 이동 처리
    }
}

/**
 * 에디터 초기화
 */
function initEditor() {
    let fontList = ['Pretendard'];
    $('#summernote').summernote({
        placeholder: '내용을 작성하세요',
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
}

$(function () {
    initEditor();
});