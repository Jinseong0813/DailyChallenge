package com.mysite.mylogin.repository;

import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository  extends JpaRepository<VerificationTokenEntity, Long> {
    Optional<VerificationTokenEntity> findByUserAndToken(UserEntity user, String token);
}
