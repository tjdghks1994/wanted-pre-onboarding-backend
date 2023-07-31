package com.wanted.repository;

import com.wanted.domain.Member;
import com.wanted.domain.MemberRole;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class MyBatisMemberRepositoryTest {

    private MemberMapper memberMapper;
    @Autowired
    public MyBatisMemberRepositoryTest(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Test
    @DisplayName("회원 저장 및 조회 테스트")
    void save_find_test() {
        // given
        Member saveMember = new Member("TEST@naver.com", "asdfoijzxciova14324dzlkvzk", MemberRole.ROLE_USER);
        // when
        memberMapper.save(saveMember);  // 회원 저장
        Member findMember = memberMapper.findById(saveMember.getMemberId()).get();  // 회원 조회
        // then
        Assertions.assertThat(findMember.getMemberId()).isEqualTo(saveMember.getMemberId());
    }
}