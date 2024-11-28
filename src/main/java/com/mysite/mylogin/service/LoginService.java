package com.mysite.mylogin.service;  
import com.mysite.mylogin.dto.LoginRequest;
import com.mysite.mylogin.dto.LoginResponse;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider; // JWT 토큰 생성 클래스 추가

    public LoginResponse login(LoginRequest request) {

        //입력받은id 바탕으로 유저엔티티 가져옴~
        UserEntity user = userRepository.findByUserid(request.getUserid())
                .orElse(null);

        //아이디 없는 경우
        if (user == null) {
            return new LoginResponse("아이디가 존재하지 않습니다.", null);
        }


        //비번이 일치하지 않은 경우
        if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new LoginResponse("비밀번호가 일치하지 않습니다.", null);
        }

        // JWT 토큰 생성
        String token = jwtTokenProvider.createToken(user.getUserid());

        return new LoginResponse("로그인 성공!", token);
    }
}