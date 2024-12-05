package com.mysite.mylogin.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // 인증 이메일 발송 메서드
    public void sendVerificationEmail(String toEmail, String token) {
        String subject = "이메일 인증 번호";
        String message = "다음 인증 번호를 입력하세요: " + token;

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("yju2001407@gmail.com");  // 발신자 이메일
        mailMessage.setTo(toEmail);                  // 수신자 이메일
        mailMessage.setSubject(subject);             // 이메일 제목
        mailMessage.setText(message);                // 이메일 본문

        javaMailSender.send(mailMessage);            // 이메일 발송
    }
}
