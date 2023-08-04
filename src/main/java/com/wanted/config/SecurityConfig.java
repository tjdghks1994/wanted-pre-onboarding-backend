package com.wanted.config;

import com.wanted.jwt.JwtAuthenticationFilter;
import com.wanted.jwt.JwtProvider;
import com.wanted.service.MemberService;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${jwt_secret_key}")
    private String jwtKey;
    private final JwtProvider jwtProvider;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .formLogin(login -> login.disable())    // form 로그인 방식 비활성화
                .httpBasic(httpBasic -> httpBasic.disable())    // bearer 방식을 사용, http basic 방식은 비활성화
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // jwt 방식이므로 세션 사용안하도록 설정
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/board/**").authenticated()    // 게시글 관련 url 은 모두 권한 필요
                                .anyRequest().permitAll()   // 나머지 요청에 대해서는 모두 허용
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtKey, jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
