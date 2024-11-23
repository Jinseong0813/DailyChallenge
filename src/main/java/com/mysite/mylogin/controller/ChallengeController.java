package com.mysite.mylogin.controller;

import com.mysite.mylogin.entity.ChallengeEntity;
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
    @GetMapping("/{userid}")
    public ResponseEntity<List<ChallengeEntity>> getChallengeByUserid(@PathVariable("userid") String userid) {
        List<ChallengeEntity> challengeEntityList = challengeService.getChallengeByUser(userid);
        return ResponseEntity.ok(challengeEntityList);
    }

//    챌린지 저장
    @PostMapping
    public ResponseEntity<ChallengeEntity> addChallenge(@RequestBody ChallengeEntity challengeEntity) {
        ChallengeEntity addChallengeEntity = challengeService.addChallenge(challengeEntity);
        return ResponseEntity.ok(addChallengeEntity);
    }
//    챌린지 삭제
    @DeleteMapping("/{challengeId}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long challengeId){
        challengeService.deleteChallenge(challengeId);
        return ResponseEntity.noContent().build();
    }
//    챌린지 업뎃
    @PutMapping("/{challengeId}")
    public ResponseEntity<ChallengeEntity> updateChallenge(@PathVariable Long challengeId, @RequestBody ChallengeEntity updatedChallenge){
        ChallengeEntity updateChallenge = challengeService.updateChallenge(challengeId ,updatedChallenge);
        return ResponseEntity.ok(updateChallenge);
    }
    // 일부 필드만 수정 (Optional)
    @PatchMapping("/{challengeId}")
    public ResponseEntity<ChallengeEntity> patchChallenge(@PathVariable Long challengeId, @RequestBody ChallengeEntity updatedChallenge) {
        ChallengeEntity updateChallenge = challengeService.updateChallenge(challengeId, updatedChallenge);
        return ResponseEntity.ok(updateChallenge);
    }


}