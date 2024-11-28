package com.mysite.mylogin.service;


import com.mysite.mylogin.entity.CalendarEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.repository.CalendarRepository;
import com.mysite.mylogin.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    public CalendarService(CalendarRepository calendarRepository, UserRepository userRepository) {
        this.calendarRepository = calendarRepository;
        this.userRepository = userRepository;
    }

    public List<CalendarEntity> getAllEvents() {
        return calendarRepository.findAll();
    }

    public Optional<CalendarEntity> getEventById(Long id) {
        return calendarRepository.findById(id);
    }

    public CalendarEntity createEvent(CalendarEntity calendarEntity, String userid) {
        UserEntity user = userRepository.findById(userid).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다 : " + userid));
        calendarEntity.setUserID(user);

        return calendarRepository.save(calendarEntity);
    }

    public CalendarEntity updateEvent(Long id,CalendarEntity calendarEntity, String userid) {
        UserEntity user = userRepository.findById(userid).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다 :" + userid));

        return calendarRepository.findById(id)
                .map(existingEntity ->{
                    existingEntity.setStartTime(calendarEntity.getStartTime());
                    existingEntity.setEndTime(calendarEntity.getEndTime());
                    existingEntity.setTitle(calendarEntity.getTitle());
                    existingEntity.setComment(calendarEntity.getComment());
                    existingEntity.setAlramStatus(calendarEntity.getAlramStatus());
                    existingEntity.setUserID(user);

                    return calendarRepository.save(existingEntity);
                }).orElseThrow(()-> new IllegalArgumentException("이벤트를 찾을 수 없습니다. :" + id));
    }

    public void deleteEvent(Long id) {
        calendarRepository.deleteById(id);
    }
}
