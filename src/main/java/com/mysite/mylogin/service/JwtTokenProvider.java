package com.mysite.mylogin.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 256비트 키 생성
    private final long validityInMilliseconds = 36000000; // 10시간 유효

    public String createToken(String userid) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(userid)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey) // 생성된 256비트 키로 서명
                .compact();
    }
}
