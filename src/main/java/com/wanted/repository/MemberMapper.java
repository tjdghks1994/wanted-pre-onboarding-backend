package com.wanted.repository;

import com.wanted.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    void save(Member member);   // 회원 가입
    Optional<Member> findById(String memberId); // 회원 조회
}
