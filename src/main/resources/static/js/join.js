function joinProc() {
    let joinMail = document.getElementById('mail-input').value;
    let joinRawPw = document.getElementById('raw-pw').value;

    let joinInfo = {
        "joinMail": joinMail,
        "joinRawPw": joinRawPw
    };

    $.ajax({
        url: '/members/join',
        method: 'post',
        contentType: 'application/json',
        data: JSON.stringify(joinInfo),
        success: function (result, statusText, jqXHR) {
            alert('회원가입에 성공했습니다. 로그인 해주세요.');
            // 회원가입이 완료되었으므로 로그인 페이지로
            window.location.href = '/members/login';
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.responseText != null) {
                alert(jqXHR.responseText);
            } else {
                alert('회원가입에 실패하였습니다. 다시 시도해주세요.');
            }
        }
    });
}