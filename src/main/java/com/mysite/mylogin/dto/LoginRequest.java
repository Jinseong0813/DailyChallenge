package com.mysite.mylogin.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "아이디를 입력해주세요")
    private String userid;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

 
}
