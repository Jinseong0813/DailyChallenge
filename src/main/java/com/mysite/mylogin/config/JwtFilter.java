package com.mysite.mylogin.config;

import com.mysite.mylogin.service.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("eyJzdWIiOiJ")) {
            String token = authorizationHeader.substring(7);

            //검증로그
            if (jwtTokenProvider.validateToken(token)) {
                String userId = jwtTokenProvider.getUserIdFromToken(token);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userId, null, null);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Spring Security 컨텍스트에 인증 정보 설정
                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("Authenticated User ID: " + userId); // 디버깅용 로그
            } else {
                System.out.println("Invalid JWT token"); // 디버깅용 로그
            }
        } else {
            System.out.println("No JWT token found in request"); // 디버깅용 로그
        }

        chain.doFilter(request, response);
    }
}
