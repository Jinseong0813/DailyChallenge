package com.mysite.mylogin.service;

import com.mysite.mylogin.entity.ThemeEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.entity.UserThemeEntity;
import com.mysite.mylogin.repository.ThemeRepository;
import com.mysite.mylogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;

    // 테마 구매 처리
    public String purchaseTheme(String userId, int themeId) {
        // 사용자 찾기
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        // 테마 찾기
        Optional<ThemeEntity> themeOptional = themeRepository.findById(themeId);
  

        UserEntity user = userOptional.get();
        ThemeEntity theme = themeOptional.get();


        // 포인트 확인 및 차감
        if (user.getPoints() < theme.getTheme_price()) {
            return "포인트가 부족합니다ㅜㅜ.";
        }

        user.subtractPoints(theme.getTheme_price());
        userRepository.save(user);

          // 구매한 테마 저장
        UserThemeEntity userTheme = new UserThemeEntity();
        userTheme.setUser(user);
        userTheme.setTheme(theme);

        return   userId + "님 (" + themeId + ") 번쨰 테마 구매 성공하셨습니다!";
    }

    // 할일 완료 처리
    public String completeTask(String userId) {

        Optional<UserEntity> userOptional = userRepository.findById(userId);
        UserEntity user = userOptional.get();

        // 포인트 추가
        user.addPoints(50);
        userRepository.save(user);

        return "수고하셨습니다 :)  포인트 50점 적립 완료";
    }

    
}



