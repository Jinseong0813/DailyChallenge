package com.mysite.mylogin.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserupdateRequest {


    private String userid;  // patch매핑 url 경로에서 받아올거임


    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+=|<>?{}\\[\\]~-]).{8,}$",
            message = "비밀번호는 최소 하나의 영문자와 하나의 특수문자를 포함한 8자리 이상으로 해야 합니다.")
    @Size(min = 6, max = 20)
    private String password;


    private String mobile;


    private String email;


}