package com.mysite.mylogin.repository;


import com.mysite.mylogin.entity.TodoListEntity;
import com.mysite.mylogin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoListEntity, Long> {
    List<TodoListEntity> findUserid(String userid);
}