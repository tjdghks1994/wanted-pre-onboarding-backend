package com.wanted.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JasyptConfig {

    @Value("${jasypt_enc_key}")
    private String encKey;  // 암호화 키

    @Bean
    @Primary
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(encKey); // 암호화 키
        config.setAlgorithm("PBEWithMD5AndDES");    // 암호화 알고리즘
        config.setKeyObtentionIterations("1000");   // 암호화 키를 얻기 위해 적용되는 해싱 반복 횟수 설정
        config.setPoolSize(1);    // 생성할 암호기 풀 크기
        config.setStringOutputType("base64");   // 인코딩 방식
        encryptor.setConfig(config);

        return encryptor;
    }
}
