package com.mysite.mylogin.controller;

import com.mysite.mylogin.dto.LoginRequest;
import com.mysite.mylogin.dto.LoginResponse;
import com.mysite.mylogin.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        LoginResponse loginresponse = loginService.login(loginRequest);

        return ResponseEntity.ok().body(loginresponse);
    }
}
