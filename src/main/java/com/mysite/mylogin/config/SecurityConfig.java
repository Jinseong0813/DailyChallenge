package com.mysite.mylogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 로그인 후 접근할 수 있는 URL
        http
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/loginProc", "/join", "/update", "/delete/**", "/update/**", "/joinProc").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/delete/**").authenticated()  // PATCH 요청에 인증 필요
                .anyRequest().permitAll()  // 나머지 요청은 모두 허용
            );

        // 로그인 페이지 및 로그인 처리 URL 설정
        http
            .formLogin((auth) -> auth
                .loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .permitAll() // 로그인 페이지는 누구나 접근 가능
            );

        // CSRF 보호 비활성화
        http
            .csrf((auth) -> auth.disable());

        // 세션 관리 설정
        http
            .sessionManagement((auth) -> auth
                .maximumSessions(5) // 최대 세션 수 설정
                .maxSessionsPreventsLogin(true) // 최대 세션 수 초과 시 로그인 방지
            );

        // 세션 고정 공격 방지
        http
            .sessionManagement((auth) -> auth
                .sessionFixation().changeSessionId() // 세션 고정 공격 방지
            );

        return http.build();
    }
}
