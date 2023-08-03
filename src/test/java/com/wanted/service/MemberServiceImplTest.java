package com.wanted.service;

import com.wanted.domain.JoinForm;
import com.wanted.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class MemberServiceImplTest {

    private final MemberService memberService;

    @Autowired
    public MemberServiceImplTest(MemberService memberService) {
        this.memberService = memberService;
    }

    @Test
    @DisplayName("회원가입/회원조회 테스트")
    void joinTest() {
        // given
        JoinForm joinForm = new JoinForm("test@gmail.com", "test1234");
        // when
        Member saveMember = memberService.joinMember(joinForm);
        Member findMember = memberService.findMember(saveMember.getMemberId()).get();
        // then
        Assertions.assertThat(saveMember.getMemberPw()).isNotEqualTo(joinForm.getJoinRawPw());
        Assertions.assertThat(findMember).isNotNull();
        Assertions.assertThat(saveMember).isEqualTo(findMember);
    }

    @Test
    @DisplayName("로그인 테스트")
    void login() {
        // given
        String username = "parktjdghks@naver.com";
        String password = "tjdghks12";
        String noUsername = "test@naver.com";
        String password2 = "tjdghks12";
        // when
        String jwtToken = memberService.login(username, password);
        // then
        Assertions.assertThat(jwtToken).isNotBlank();
        Assertions.assertThatThrownBy(() -> memberService.login(noUsername, password2)).isInstanceOf(IllegalArgumentException.class);
    }
}