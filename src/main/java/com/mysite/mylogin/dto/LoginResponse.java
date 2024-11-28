package com.mysite.mylogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String message;  // 로그인 성공 또는 실패 메시지
    private String token;    // 로그인 성공 시 반환되는 JWT 토큰


    public LoginResponse(String message) {
        this.message = message;
    }
}
