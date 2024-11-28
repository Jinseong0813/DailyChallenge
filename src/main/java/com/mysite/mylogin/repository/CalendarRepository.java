package com.mysite.mylogin.repository;

import com.mysite.mylogin.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

}
