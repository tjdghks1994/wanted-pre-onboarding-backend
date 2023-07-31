package com.wanted.config;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class SecurityConfigTest {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfigTest(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Test
    @DisplayName("단방향 암호화 테스트")
    void bCryptPasswordEncoderTest() {
        // given
        String rawPassword = "test1234";
        // when
        String encPassword = passwordEncoder.encode(rawPassword);
        boolean matches = passwordEncoder.matches(rawPassword, encPassword);
        // then
        Assertions.assertThat(rawPassword).isNotEqualTo(encPassword);
        Assertions.assertThat(matches).isTrue();
    }
}