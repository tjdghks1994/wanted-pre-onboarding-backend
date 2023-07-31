package com.wanted.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class Member {
    private Long memberNo;  // 회원번호
    private String memberId;    // 회원아이디(이메일)
    private String memberPw;    // 회원패스워드(암호화)
    private MemberRole memberRole;  // 회원권한(사용자,관리자)

    public Member() { }
    public Member(String memberId, String memberPw, MemberRole memberRole) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberRole = memberRole;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Member)) {
            throw new IllegalArgumentException("Argument Type is Not Member");
        }
        Member member = (Member) obj;
        // 회원 ID가 동일하면 동일한 회원임을 의미
        return member.getMemberId().equals(this.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(memberPw);
    }
}
