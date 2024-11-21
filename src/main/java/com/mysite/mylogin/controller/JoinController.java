package com.mysite.mylogin.controller;

import com.mysite.mylogin.dto.JoinRequest;
import com.mysite.mylogin.dto.JoinResponse;
import com.mysite.mylogin.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<JoinResponse> joinProcess(@RequestBody JoinRequest request) {
        JoinResponse response = joinService.joinProcess(request);
        return ResponseEntity.ok().body(response);
    }
}