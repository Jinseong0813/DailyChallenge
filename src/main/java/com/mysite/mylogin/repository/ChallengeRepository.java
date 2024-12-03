package com.mysite.mylogin.repository;

import com.mysite.mylogin.entity.ChallengeEntity;
import com.mysite.mylogin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long>{
    List<ChallengeEntity> findByUserid(UserEntity userid);
}
