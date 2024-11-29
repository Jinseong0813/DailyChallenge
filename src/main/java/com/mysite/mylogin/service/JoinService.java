package com.mysite.mylogin.service;


import com.mysite.mylogin.dto.JoinRequest;
import com.mysite.mylogin.dto.JoinResponse;
import com.mysite.mylogin.entity.ThemeEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.entity.UserThemeEntity;
import com.mysite.mylogin.repository.ThemeRepository;
import com.mysite.mylogin.repository.UserRepository;
import com.mysite.mylogin.repository.UserThemeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final ThemeRepository themeRepository; 
    private final UserThemeRepository userThemeRepository; 
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinResponse joinProcess(JoinRequest request) {

        boolean isUser = userRepository.existsById(request.getUserid());
        if (isUser) {
            return new JoinResponse("이미 존재하는 아이디입니다.");
        }

        boolean isEmailExist = userRepository.existsByEmail(request.getEmail());
        if (isEmailExist) {
            return new JoinResponse("이미 존재하는 이메일입니다.");
        }

        // 비밀번호 같은지 확인
        if (!request.getPassword().equals(request.getPassword2())) {
            return new JoinResponse("비밀번호가 다릅니다.");
        }

        // userEntity 엔티티에 유저정보 저장하고(이메일이랑 비밀번호는 암호화)
        UserEntity data = new UserEntity();
        data.setUserid(request.getUserid());
        data.setMobile(request.getMobile());
        data.setEmail(request.getEmail());  // 이메일을 암호화하지 않음 (원본 이메일을 저장)
        data.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        // /기본 테마 설정

        ThemeEntity defaultTheme = themeRepository.findById(1).orElse(null);
        data.setTheme(defaultTheme);

        userRepository.save(data);


        ///유저가 구매한테마 저장하는 usertheme 엔티티에 디폴트 테마 추가
        UserThemeEntity userTheme = new UserThemeEntity();
        userTheme.setUser(data);
        userTheme.setTheme(defaultTheme);
        userThemeRepository.save(userTheme);



        // 기본적으로 USER 권한을 부여
        //data.getRoles().add("USER");  // "USER" 권한을 부여
        return new JoinResponse("회원 가입 되었습니다.");
    }
}