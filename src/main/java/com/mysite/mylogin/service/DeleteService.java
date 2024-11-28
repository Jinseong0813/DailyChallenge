package com.mysite.mylogin.service;

import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteService {

    private final UserRepository userRepository;

    public boolean deleteUser(String userid) {
        UserEntity user = userRepository.findById(userid).orElse(null);

        if (user != null) {
            user.setStatus(0); // 상태를 비활성화로 설정
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
