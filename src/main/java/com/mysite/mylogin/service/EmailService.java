package com.mysite.mylogin.service;

import lombok.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;  // JavaMailSender 객체 주입

    @Value("${spring.mail.username}")
    private String senderEmail;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // 이메일로 인증번호 보내는 메서드
    public void sendVerificationEmail(String toEmail, String token) {
        // 인증번호 이메일 내용 구성
        String subject = "이메일 인증 번호";
        String message = "다음 인증 번호를 입력하세요: " + token;

        // 이메일 전송 객체 생성
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(senderEmail);  // 발송 이메일 주소
        mailMessage.setTo(toEmail);  // 수신 이메일 주소
        mailMessage.setSubject(subject);  // 이메일 제목
        mailMessage.setText(message);  // 이메일 본문

        // 이메일 전송
        javaMailSender.send(mailMessage);
    }
}
