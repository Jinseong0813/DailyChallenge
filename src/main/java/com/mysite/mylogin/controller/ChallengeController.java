package com.mysite.mylogin.controller;


import com.mysite.mylogin.dto.AddChallengeRequest;
import com.mysite.mylogin.dto.ChallengeRequest;
import com.mysite.mylogin.dto.ChallengeResponse;
import com.mysite.mylogin.entity.ChallengeEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class ChallengeController {
    private final ChallengeService challengeService;

//    유저 조회
@GetMapping("/{userId}")
    public ResponseEntity<List<ChallengeEntity>> getChallengeByUserid(@PathVariable String userId) {
        List<ChallengeEntity> challengeList = challengeService.getChallengeByUser(userId);
        return ResponseEntity.ok(challengeList);
}

//    챌린지 저장
@PostMapping("/add/{userId}")
    public ResponseEntity<ChallengeResponse> addChallenge(@PathVariable String userId, @RequestBody AddChallengeRequest addChallengeRequest) {
        ChallengeResponse saveChallenge = challengeService.addChallenge(userId , addChallengeRequest);
        return ResponseEntity.ok(saveChallenge);
    }
//    챌린지 삭제
    @DeleteMapping("/delete/{challengeId}")
    public ResponseEntity<ChallengeResponse> deleteChallenge(@PathVariable Long challengeId){
        ChallengeResponse delete=challengeService.deleteChallenge(challengeId);
        ResponseEntity.noContent().build();
        return ResponseEntity.ok(delete);
    }
//    챌린지 업뎃
    @PatchMapping("/update/{challengeId}")
    public ResponseEntity<ChallengeResponse> updateChallenge(@PathVariable Long challengeId, @RequestBody ChallengeRequest updatedChallenge){
        ChallengeResponse updateChallenge = challengeService.updateChallenge(challengeId ,updatedChallenge);
        return ResponseEntity.ok(updateChallenge);
    }



}