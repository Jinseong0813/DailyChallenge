package com.mysite.mylogin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mysite.mylogin.dto.LoginResponse;
import com.mysite.mylogin.dto.UserupdateRequest;
import com.mysite.mylogin.dto.UserupdateResponse;
import com.mysite.mylogin.service.UserupdateService;

@RestController
public class UserupdateController {

    @Autowired
    private UserupdateService userupdateService;

    @PatchMapping("/update/{userid}")
    public ResponseEntity<UserupdateResponse> updateUserInfo(@PathVariable String userid, @RequestBody UserupdateRequest request) {
      try {
          request.setUserid(userid); 
          UserupdateResponse updateresponse = userupdateService.updateUserInfo(request);
          return ResponseEntity.ok().body(updateresponse);
       } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
}