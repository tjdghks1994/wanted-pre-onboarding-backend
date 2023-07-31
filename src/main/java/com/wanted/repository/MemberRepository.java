package com.wanted.repository;

import com.wanted.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);   // 회원 가입
    Optional<Member> findById(String memberId); // 회원 조회
}
