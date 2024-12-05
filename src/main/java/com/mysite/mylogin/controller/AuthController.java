package com.mysite.mylogin.controller;


import com.mysite.mylogin.dto.LoginRequest;
import com.mysite.mylogin.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // 사용자 인증 로직 (예: DB에서 사용자 확인)
        String userId = authenticate(loginRequest);

        if (userId != null) {
            String token = jwtTokenProvider.createToken(userId); // 토큰 생성
            return ResponseEntity.ok(Map.of("token", token, "userId", userId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    private String authenticate(LoginRequest loginRequest) {
        // 사용자 인증 로직 작성
        if ("testUser".equals(loginRequest.getUserid()) && "password".equals(loginRequest.getPassword())) {
            return "testUserId";
        }
        return null;
    }
}
