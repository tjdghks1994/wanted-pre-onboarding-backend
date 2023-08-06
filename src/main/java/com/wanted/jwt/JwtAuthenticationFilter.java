package com.wanted.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final String jwtKey;
    private final JwtProvider jwtProvider;
    
    public JwtAuthenticationFilter(String jwtKey, JwtProvider jwtProvider) {
        this.jwtKey = jwtKey;
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.debug("====[ JwtAuthenticationFilter doFilterInternal Start ] ====");
        // authorization 헤더 값 꺼내기
        String authorization = jwtProvider.resolveToken(request);
        if (authorization != null && !jwtProvider.validateToken(authorization ,jwtKey)) {
            // jwt
            String token = authorization.split(" ")[1];
            // jwt 에서 username 꺼내기
            String username = jwtProvider.getMemberId(token, jwtKey);
            // jwt 에서 사용자 권한 목록 꺼내기
            String memberRole = jwtProvider.getMemberRole(token, jwtKey);
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(memberRole);
            // 권한 부여
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, List.of(authority));
            // Detail 넣어줌
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = {"/members/login", "/members/join", "/js", "/css", "/summernote"};
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }
}
