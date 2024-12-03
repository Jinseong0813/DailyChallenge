package com.mysite.mylogin.service;


import com.mysite.mylogin.Exception.CalendarNotFoundException;
import com.mysite.mylogin.convert.CalendarConvert;
import com.mysite.mylogin.dto.CalendarRequest;
import com.mysite.mylogin.dto.CalendarResponse;
import com.mysite.mylogin.entity.CalendarEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.repository.CalendarRepository;
import com.mysite.mylogin.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalendarService {
    private final UserRepository userRepository;
    private final CalendarRepository calendarRepository;

    public CalendarService(UserRepository userRepository, CalendarRepository calendarRepository) {
        this.userRepository = userRepository;
        this.calendarRepository = calendarRepository;
    }



    //캘린더 생성 서비스
    public CalendarResponse createCalendar(String userid, CalendarRequest calendarRequest) {
        UserEntity user = userRepository.findByUserid(userid)
                .orElseThrow(() -> new RuntimeException("User not found : " + userid));

        CalendarEntity calendarEntity = CalendarConvert.toEntity(calendarRequest, user);

        CalendarEntity savedCalendar = calendarRepository.save(calendarEntity);

        return CalendarConvert.toDto(savedCalendar);
    }

    //캘린더 수정 서비스
    public CalendarResponse updateCalendar(Long calendarId, CalendarRequest calendarRequest, UserEntity userid) {
        CalendarEntity entity = calendarRepository.findByIdAndUserId(calendarId, userid)
                .orElseThrow(() -> new RuntimeException("Calendar not found : " + calendarId));

        entity.setTitle(calendarRequest.getTitle());
        entity.setStartTime(calendarRequest.getStartTime());
        entity.setEndTime(calendarRequest.getEndTime());
        entity.setStartDate(calendarRequest.getStartDate());
        entity.setEndDate(calendarRequest.getEndDate());
        entity.setComment(calendarRequest.getComment());
        entity.setAlramStatus(calendarRequest.getAlramStatus());

        CalendarEntity updatedEntity = calendarRepository.save(entity);

        return CalendarConvert.toDto(updatedEntity);
    }

    //일정 삭제
    public void deleteCalendar(Long calendarId, UserEntity user) {
        // Fetch the existing calendar event
        CalendarEntity entity = calendarRepository.findByIdAndUserId(calendarId, user)
                .orElseThrow(() -> new RuntimeException("Event not found: " + calendarId));

        // Delete the calendar event
        calendarRepository.delete(entity);
    }

    //모든 일정 조회 서비스
    public List<CalendarResponse> getCalendars(String userid) {
        UserEntity user = userRepository.findById(userid)
                .orElseThrow(() -> new RuntimeException("User not found : " + userid));

        List<CalendarEntity> events = calendarRepository.findAllByUserId(user);

        return events.stream()
                .map(CalendarConvert::toDto)
                .collect(Collectors.toList());
    }

    //유저의 선택 일정 조회 서비스
    public CalendarResponse getEventByIdAndUserId(Long calendarId, String userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        CalendarEntity entity = calendarRepository.findByIdAndUserId(calendarId, user)
                .orElseThrow(() -> new RuntimeException("Calendar not found: " + calendarId));

        return CalendarConvert.toDto(entity);
    }

}
