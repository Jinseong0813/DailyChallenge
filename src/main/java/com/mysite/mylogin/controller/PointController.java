package com.mysite.mylogin.controller;

import com.mysite.mylogin.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {

    private final ThemeService themeService;

    // 테마 구매 API
    @PostMapping("/purchase/{userId}/{themeId}")
    public ResponseEntity<String> purchaseTheme(
            @PathVariable String userId,
            @PathVariable int themeId) {
        String response = themeService.purchaseTheme(userId, themeId);
        return ResponseEntity.ok(response);
    }

    // 할일 완료 후 포인트 추가 API
    @PostMapping("/complete/{userId}")
    public ResponseEntity<String> completeTask(@PathVariable String userId) {
        String response = themeService.completeTask(userId);
        return ResponseEntity.ok(response);
    }
}
