package com.mysite.mylogin.repository;

import com.mysite.mylogin.entity.CalendarEntity;
import com.mysite.mylogin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {
    List<CalendarEntity> findByUserid(UserEntity userid);

    @Query("SELECT c FROM CalendarEntity c where c.userid = :user")
    List<CalendarEntity> findAllByUserId(@Param("user") UserEntity user);

    @Query("SELECT c FROM CalendarEntity c WHERE c.calendarId = :calendarId AND c.userid = :user")
    Optional<CalendarEntity> findByIdAndUserId(
            @Param("calendarId") Long calendarId,
            @Param("user") UserEntity userid);
}
