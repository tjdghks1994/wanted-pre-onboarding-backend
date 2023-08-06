package com.wanted.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class JoinForm {
    @NotBlank   // null, "", " " 모두 허용하지 않음
    private String joinMail; // 회원이 입력한 이메일 ID
    @NotBlank
    @Size(min = 8)  // 8자 이상
    private String joinRawPw;   // 회원이 입력한 비밀번호

    public JoinForm() { }

    public JoinForm(String joinMail, String joinRawPw) {
        this.joinMail = joinMail;
        this.joinRawPw = joinRawPw;
    }
}
