package com.mysite.mylogin.controller;

import com.mysite.mylogin.dto.JoinRequest;
import com.mysite.mylogin.dto.JoinResponse;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.service.JoinService;
import com.mysite.mylogin.service.VerificationService;
import com.mysite.mylogin.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/verification")
public class VerificationController {

    private final VerificationService verificationService;
    private final JoinService joinService;
    private final EmailService emailService;

    // 인증번호 발송
    @PostMapping("/send/{userId}")
    public ResponseEntity<String> sendVerificationToken(@PathVariable String userId) {
        // JoinService를 통해 사용자 정보를 조회
        UserEntity user = joinService.findUserById(userId); // joinService 메서드를 사용하여 유저 정보 조회
        if (user == null) {
            return ResponseEntity.badRequest().body("사용자가 존재하지 않습니다.");
        }

        // VerificationService를 사용하여 인증 토큰 생성
        String token = verificationService.createVerificationToken(user);

        // 이메일 발송
        emailService.sendVerificationEmail(user.getEmail(), token);

        return ResponseEntity.ok("인증번호가 발송되었습니다.");
    }

    // 인증번호 확인
    @PostMapping("/verify")
    public ResponseEntity<String> verifyToken(@RequestParam String userId, @RequestParam String token) {
        // JoinService를 통해 사용자 정보를 조회
        UserEntity user = joinService.findUserById(userId); // joinService 메서드를 사용하여 유저 정보 조회
        if (user == null) {
            return ResponseEntity.badRequest().body("사용자가 존재하지 않습니다.");
        }

        // 인증 토큰 유효성 검사
        boolean isValid = verificationService.verifyToken(user, token);
        if (!isValid) {
            return ResponseEntity.badRequest().body("인증번호가 잘못되었거나 만료되었습니다.");
        }
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }
}
