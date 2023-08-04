function loginProc() {
    let memberId = document.getElementById('memberId').value;
    let memberPw = document.getElementById('memberPw').value;

    let loginInfo = {
        "memberId": memberId,
        "memberPw": memberPw
    };

    $.ajax({
        url: "/members/login",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(loginInfo),
        success: function (result, statusText, jqXHR) {
            // 로그인 완료되었으므로 /로 이동처리
            window.location.href = '/';
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.responseText != null) {
                alert(jqXHR.responseText);
            } else {
                alert('로그인에 실패하였습니다. 다시 시도해주세요.');
            }
        }
    });
}