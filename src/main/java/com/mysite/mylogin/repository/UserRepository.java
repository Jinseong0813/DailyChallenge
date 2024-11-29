package com.mysite.mylogin.repository;

import com.mysite.mylogin.entity.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    

    //널 값 나올거 대비해서 optional로 변환
    Optional<UserEntity> findByUserid(String userid);
    
    //중복검증
    boolean existsByUserid(String userid);
    boolean existsByEmail(String email);

    
}



