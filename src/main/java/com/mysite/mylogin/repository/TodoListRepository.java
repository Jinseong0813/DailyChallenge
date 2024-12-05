package com.mysite.mylogin.repository;

import com.mysite.mylogin.entity.TodoListEntity;
import com.mysite.mylogin.entity.UserEntity;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoListEntity, Long> {
    List<TodoListEntity> findByUserid(UserEntity userid);  // 반환 타입을 Optional -> List로 변경
   
   

    @Query("SELECT t.title, SUM(t.repeatCount) as totalRepeatCount " +
    "FROM TodoListEntity t " +
    "WHERE t.userid.id = :userId AND t.dueDate BETWEEN :startDate AND :endDate " +
    "GROUP BY t.title " +
    "ORDER BY totalRepeatCount DESC")
    List<Object[]> findTopUsedTodos(String userId, LocalDateTime startDate, LocalDateTime endDate);






}
