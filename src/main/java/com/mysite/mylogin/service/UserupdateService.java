package com.mysite.mylogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mysite.mylogin.dto.UserupdateRequest;
import com.mysite.mylogin.dto.UserupdateResponse;  // UserupdateResponse를 import 합니다.
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.repository.UserRepository;

@Service
public class UserupdateService {

    @Autowired
    private UserRepository userRepository;

    // 사용자 정보 수정
    public UserupdateResponse updateUserInfo(UserupdateRequest request) {
        // 사용자 정보 조회
        UserEntity userEntity = userRepository.findByUserid(request.getUserid()).orElse(null);
    
        // 만약 userEntity가 null이라면, 예외를 던지지 않고 기존 데이터를 유지하는 방식으로 처리
        if (userEntity == null) {
            return new UserupdateResponse("해당 사용자는 존재하지 않습니다.");
        }
    
        // password가 null이 아니면 새로운 값으로 업데이트
        if (request.getPassword() != null) {
            userEntity.setPassword(request.getPassword());
        }
    
        // mobile이 null이 아니면 새로운 값으로 업데이트
        if (request.getMobile() != null) {
            userEntity.setMobile(request.getMobile());
        }
    
        // email이 null이 아니면 새로운 값으로 업데이트
        if (request.getEmail() != null) {
            userEntity.setEmail(request.getEmail());
        }
    
        // 수정된 사용자 정보 저장
        userRepository.save(userEntity);
    
        // 성공 메시지 포함된 UserupdateResponse 객체 반환
        return new UserupdateResponse("회원 정보가 성공적으로 수정되었습니다.");
    }
}
