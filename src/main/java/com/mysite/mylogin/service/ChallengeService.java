package com.mysite.mylogin.service;


import com.mysite.mylogin.entity.ChallengeEntity;
import com.mysite.mylogin.entity.TodoListEntity;
import com.mysite.mylogin.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

//  사용자의 챌린지 조회
    public List<ChallengeEntity> getChallengeByUser(String userid){ return challengeRepository.findByUserId(userid); }
// 챌린지 저장
    public ChallengeEntity addChallenge(ChallengeEntity challenge){ return challengeRepository.save(challenge); }
//    챌린지 일부 업뎃, 전체 업뎃
    public ChallengeEntity updateChallenge(Long challengeId, ChallengeEntity updatedChallenge) {
        ChallengeEntity existingChallenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 챌린지를 찾을 수 없습니다. ID: " + challengeId));

        // 전달된 필드만 업데이트
        if (updatedChallenge.getTitle() != null) {
            existingChallenge.setTitle(updatedChallenge.getTitle());
        }
        if (updatedChallenge.getComment() != null) {
            existingChallenge.setComment(updatedChallenge.getComment());
        }
        if (updatedChallenge.getStatus() != null) {
            existingChallenge.setStatus(updatedChallenge.getStatus());
        }
        if (updatedChallenge.getStart() != null) {
            existingChallenge.setStart(updatedChallenge.getStart());
        }
        if (updatedChallenge.getFinish() != null) {
            existingChallenge.setFinish(updatedChallenge.getFinish());
        }

        return challengeRepository.save(existingChallenge);
    }
//    챌린지 삭제
    public void deleteChallenge(Long challengeId){ challengeRepository.deleteById(challengeId); }


}
