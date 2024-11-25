package com.mysite.mylogin.controller;

import com.mysite.mylogin.service.DeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteController {

    private final DeleteService deleteService;

    // PATCH 메서드를 사용하여 상태를 비활성화 처리
    @PatchMapping("/delete/{userid}")
    public ResponseEntity<String> deactivateUser(@PathVariable String userid) {
        boolean isDeactivated = deleteService.deleteUser(userid);  // 상태 비활성화 메서드 호출

        if (isDeactivated) {
            return ResponseEntity.ok("User has been deactivated successfully.");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }
}
