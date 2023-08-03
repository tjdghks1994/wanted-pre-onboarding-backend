package com.wanted.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginInfo {
    @NotBlank
    private String memberId;
    @NotBlank
    @Size(min = 8)
    private String memberPw;
}
