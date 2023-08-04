package com.wanted.jwt;

import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    // 토큰에서 사용자 권한 목록 꺼내기
    public String getMemberRole(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("role", String.class);
    }

    // 토큰에서 사용자 ID 까내기
    public String getMemberId(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("username", String.class);
    }
    // 토큰 유효성 + 만료일자 체크
    public boolean validateToken(String authorization, String secretKey) {
        String token = authorization.split(" ")[1];
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                    .getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            log.error("token is validate Error !!", e);
            return true;
        }
    }
    // Authorization Header 값 반환
    // 만약 없는 경우, 쿠키에 있는 값 조회해서 반환
    public String resolveToken(HttpServletRequest request) {
        if (request.getHeader("Authorization") == null) {
            Cookie[] list = request.getCookies();
            for (Cookie cookie : list) {
                if (cookie.getName().equals("access_token")) {
                    return "Bearer " + cookie.getValue();
                }
            }
        }
        return request.getHeader("Authorization");
    }
    // jwt 생성
    public String createJwt(String memberName, String memberRole, String secretKey, Long expiredMs) {
        Claims claims = Jwts.claims();
        claims.put("username", memberName);
        claims.put("role", memberRole);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 생성(발행)일자
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))    // 만료 일자
                .signWith(SignatureAlgorithm.HS256, secretKey) // 비밀키와 알고리즘을 사용해 서명
                .compact();
    }
}
