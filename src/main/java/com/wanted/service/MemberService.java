package com.wanted.service;

import com.wanted.domain.JoinForm;
import com.wanted.domain.Member;

import java.util.Optional;

public interface MemberService {
    Member joinMember(JoinForm joinForm); // 회원 가입
    Optional<Member> findMember(String memberId);   // 회원 조회
}
