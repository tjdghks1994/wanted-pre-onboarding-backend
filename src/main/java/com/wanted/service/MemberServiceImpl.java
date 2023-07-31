package com.wanted.service;

import com.wanted.domain.JoinForm;
import com.wanted.domain.Member;
import com.wanted.domain.MemberRole;
import com.wanted.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Member joinMember(JoinForm joinForm) {
        String rawPassword = joinForm.getJoinRawPw();   // 암호화 전 평문 패스워드
        String encPassword = passwordEncoder.encode(rawPassword);
        // Member 객체로 변환
        Member member = new Member();
        member.setMemberId(joinForm.getJoinMail());
        member.setMemberPw(encPassword);
        member.setMemberRole(MemberRole.ROLE_USER);
        // 회원 가입
        memberRepository.save(member);

        return member;
    }

    @Override
    public Optional<Member> findMember(String memberId) {
        return memberRepository.findById(memberId);
    }
}
