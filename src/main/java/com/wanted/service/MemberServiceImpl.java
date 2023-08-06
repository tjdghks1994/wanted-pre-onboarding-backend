package com.wanted.service;

import com.wanted.domain.JoinForm;
import com.wanted.domain.Member;
import com.wanted.domain.MemberRole;
import com.wanted.jwt.JwtProvider;
import com.wanted.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    @Value("${jwt_secret_key}")
    private String jwtKey;
    private Long expireTimeMs = 1000 * 60 * 30L; // jwt 만료 시간 30분
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
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
        // 회원 가입 전 이미 존재한 아이디 인지 체크
        Optional<Member> findById = memberRepository.findById(member.getMemberId());
        if (findById.isPresent()) { // 이미 존재
            throw new IllegalArgumentException("이미 가입된 이메일 입니다 = " + member.getMemberId());
        }
        // 회원 가입
        memberRepository.save(member);

        return member;
    }

    @Override
    public Optional<Member> findMember(String memberId) {
        return memberRepository.findById(memberId);
    }
    @Override
    public String login(String username, String password) {
        // 사용자 조회
        Member findMember = memberRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException(username + " 은(는) 없습니다."));
        // 비밀번호 틀린 경우
        if (!(passwordEncoder.matches(password, findMember.getMemberPw()))) {
            throw new IllegalArgumentException("패스워드를 잘못 입력했습니다.");
        }
        // jwt 생성
        String token = jwtProvider.createJwt(findMember.getMemberId(), findMember.getMemberRole().name(), jwtKey, expireTimeMs);

        return token;
    }
}
