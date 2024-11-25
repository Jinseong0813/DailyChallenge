package com.mysite.mylogin.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinRequest {


    @NotBlank
    @Size(min = 6, max = 20)
    private String userid;


    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+=|<>?{}\\[\\]~-]).{8,}$",
            message = "비밀번호는 최소 하나의 영문자와 하나의 특수문자를 포함한 8자리 이상으로 해야 합니다.")
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;


    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+=|<>?{}\\[\\]~-]).{8,}$",
    message = "비밀번호는 최소 하나의 영문자와 하나의 특수문자를 포함한 8자리 이상으로 해야 합니다.")
    @NotBlank
    @Size(min = 6, max = 20)
    private String password2;


    @Email(message = "올바른 이메일 주소를 입력해주세요")
 
    @NotBlank
    private String email;



    @NotBlank
    private String mobile;
}


