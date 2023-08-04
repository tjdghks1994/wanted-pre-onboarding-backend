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