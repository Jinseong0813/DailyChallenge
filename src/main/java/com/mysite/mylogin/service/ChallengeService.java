package com.mysite.mylogin.service;



import com.mysite.mylogin.dto.AddChallengeRequest;
import com.mysite.mylogin.dto.ChallengeRequest;
import com.mysite.mylogin.dto.ChallengeResponse;
import com.mysite.mylogin.entity.ChallengeEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.repository.ChallengeRepository;
import com.mysite.mylogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

//  사용자의 챌린지 조회
    public List<ChallengeEntity> getChallengeByUser(String userId){
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("유저의 아이디를 찾을 수 없습니다." +userId));
        return challengeRepository.findByUserid(userEntity);
    }
// 챌린지 추가
    public ChallengeResponse addChallenge(String userId, AddChallengeRequest addChallengeRequest) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("유저의 아이디를 찾을수 없습니다. " + userId));
        ChallengeEntity challengeEntity = new ChallengeEntity();
        challengeEntity.setUserid(user);
        challengeEntity.setFinish(addChallengeRequest.getFinish());
        challengeEntity.setStatus(addChallengeRequest.getStatus());
        challengeEntity.setComment(addChallengeRequest.getComment());
        challengeEntity.setStart(addChallengeRequest.getStart());
        challengeEntity.setTitle(addChallengeRequest.getTitle());
        challengeRepository.save(challengeEntity);
        return new ChallengeResponse("추가되었습니다.");

}
//    챌린지 전체 업뎃
    public ChallengeResponse updateChallenge(Long challengeId, ChallengeRequest updatedChallenge) {
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
        challengeRepository.save(existingChallenge);
        return new ChallengeResponse("변경이 완료되었습니다.");
    }
//    챌린지 삭제
    public ChallengeResponse deleteChallenge(Long challengeId){
        challengeRepository.deleteById(challengeId);
        return new ChallengeResponse("삭제되었습니다.");

    }


}
