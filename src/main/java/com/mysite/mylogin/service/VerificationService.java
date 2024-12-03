package com.mysite.mylogin.service;


import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mysite.mylogin.entity.VerificationTokenEntity;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final VerificationTokenRepository verificationTokenRepository;

    // 인증번호 생성 및 저장
    public String createVerificationToken(UserEntity user) {
        String token = generateRandomToken();
        VerificationTokenEntity verificationToken = new VerificationTokenEntity();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(10));  // 인증번호 만료 시간 설정 (10분)

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    // 인증번호 유효성 검사
    public boolean verifyToken(UserEntity user, String token) {
        VerificationTokenEntity verificationToken = verificationTokenRepository.findByUserAndToken(user, token)
                .orElse(null);

        if (verificationToken == null) {
            return false;  // 인증번호가 존재하지 않음
        }

        // 만료 시간을 체크
        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return false;  // 만료됨
        }

        return true;  // 인증 성공
    }

    // 랜덤 인증번호 생성 메서드
    private String generateRandomToken() {
        Random random = new Random();
        int token = 100000 + random.nextInt(900000);  // 6자리 인증번호 생성
        return String.valueOf(token);
    }
}

