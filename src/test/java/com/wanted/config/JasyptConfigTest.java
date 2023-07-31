package com.wanted.config;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class JasyptConfigTest {
    private StringEncryptor encryptor;

    @Autowired
    public JasyptConfigTest(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Test
    void stringEncryptor() {
        // given
        String plainText = "test1234";
        // when
        String encText = encryptor.encrypt(plainText);
        // then
        Assertions.assertThat(encText).isNotEqualTo(plainText);
    }
}