package com.mysite.mylogin.repository;

import com.mysite.mylogin.entity.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    

    //Optional로 감싸면, null이 아닐 때만 값이 존재하는 것처럼 처리할 수 있어서, null을 처리하는 데 더 안전한 방법입니다.
    //널 값 나올거 대비해서 optional로 변환
    Optional<UserEntity> findByUserid(String userid);
    
    //중복검증
    boolean existsByUserid(String userid);

    
}



