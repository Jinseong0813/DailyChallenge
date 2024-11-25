package com.mysite.mylogin.service;


import com.mysite.mylogin.dto.JoinRequest;
import com.mysite.mylogin.dto.JoinResponse;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

        // userEntity 엔티티에 유저정보 저장하고(이메일이랑 비밀번호는 암호화)
        UserEntity data = new UserEntity();
        data.setUserid(request.getUserid());
        data.setMobile(request.getMobile());
        data.setEmail(request.getEmail());  // 이메일을 암호화하지 않음 (원본 이메일을 저장)
        data.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        // 기본적으로 USER 권한을 부여
        //data.getRoles().add("USER");  // "USER" 권한을 부여

        userRepository.save(data);

        return new JoinResponse("회원 가입 되었습니다.");
    }
}