package com.mysite.mylogin.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final Key secretKey; // 256비트 키 생성
    private final long validityInMilliseconds; // 10시간 유효

    public JwtTokenProvider(@Value("${jwt.secret}")String secret,
                            @Value("${jwt.expiration}")long validityInMilliseconds) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String createToken(String userid) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        System.out.println("Create token for userID :" + userid);

        return Jwts.builder()
                .setSubject(userid)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS256) // 생성된 256비트 키로 서명
                .compact();
    }

    //JWT에서 사용자ID 추출
    public String getUserIdFromToken(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(secretKey)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        return claims.getSubject();//subject에서 아이디 가져옴
        try{
            String userId = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();


            //디버깅
            System.out.println("Decoded User ID : " + userId);

            return userId;

        }catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid JWT token:" + e.getMessage());
            return null;
        }
    }

    //JWT 유요성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            //유요하면 true
            return true;
        }catch (ExpiredJwtException e){
            System.out.println("Token expired" + e.getMessage());
            return false;
        } catch (JwtException | IllegalArgumentException ex) {
            System.out.println("Token invalid" + ex.getMessage());
            //유효하지 않으면 false
            return false;
        }
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
