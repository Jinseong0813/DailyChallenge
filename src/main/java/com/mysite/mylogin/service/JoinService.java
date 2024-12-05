package com.mysite.mylogin.service;

import com.mysite.mylogin.dto.JoinRequest;
import com.mysite.mylogin.dto.JoinResponse;
import com.mysite.mylogin.entity.ThemeEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.entity.UserThemeEntity;
import com.mysite.mylogin.repository.ThemeRepository;
import com.mysite.mylogin.repository.UserRepository;
import com.mysite.mylogin.repository.UserThemeRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final UserThemeRepository userThemeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    private final String SECRET_KEY = "dsfsdfewkljflksedjflksdjr34276574565";  // JWT secret key (보안 관리 필요)

    // 회원가입 처리
    public JoinResponse joinProcess(JoinRequest request) {
        boolean isUser = userRepository.existsById(request.getUserid());

        // 이미 유저 id가 존재할 경우
        if (isUser) {
            return new JoinResponse("이미 가입된 회원입니다.");
        }

        // 비밀번호 같은지 확인
        if (!request.getPassword().equals(request.getPassword2())) {
            return new JoinResponse("비밀번호가 다릅니다.");
        }

        // UserEntity 엔티티에 유저정보 저장
        UserEntity data = new UserEntity();
        data.setUserid(request.getUserid());
        data.setMobile(request.getMobile());
        data.setEmail(request.getEmail());
        data.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        // 기본 테마 설정
        ThemeEntity defaultTheme = themeRepository.findById(1).orElse(null);
        data.setTheme(defaultTheme);

        userRepository.save(data);

        // 유저가 구매한 테마 저장하는 userTheme 엔티티에 디폴트 테마 추가
        UserThemeEntity userTheme = new UserThemeEntity();
        userTheme.setUser(data);
        userTheme.setTheme(defaultTheme);
        userThemeRepository.save(userTheme);


        return new JoinResponse("회원 가입 되었습니다. ");
    }




    
}
