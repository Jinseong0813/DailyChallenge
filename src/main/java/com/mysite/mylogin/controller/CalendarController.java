package com.mysite.mylogin.controller;


import com.mysite.mylogin.convert.CalendarConvert;
import com.mysite.mylogin.dto.CalendarRequest;
import com.mysite.mylogin.dto.CalendarResponse;
import com.mysite.mylogin.entity.CalendarEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.service.CalendarService;
import com.mysite.mylogin.service.UserupdateService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }


    //캘린더 일정 생성
    @PostMapping("/create/{userid}")
    public ResponseEntity<CalendarResponse>createCalendar(@PathVariable("userid") String userid, @RequestBody @Valid CalendarRequest calendarRequest) {
        log.info("Create calendar for user id {}", userid);

        CalendarResponse response = calendarService.createCalendar(userid, calendarRequest);

        return ResponseEntity.ok(response);
    }

    //캘린더 일정 수정
    @PutMapping("/update/{userid}/{calendarId}")
    public ResponseEntity<CalendarResponse> updateCalendar(@PathVariable String userid, @PathVariable Long calendarId, @RequestBody @Valid CalendarRequest calendarRequest) {
        UserEntity user = new UserEntity();
        user.setUserid(userid);
        CalendarResponse response = calendarService.updateCalendar(calendarId, calendarRequest, user);
        return ResponseEntity.ok(response);
    }

    //캘린더 일정 삭제
    @DeleteMapping("/delete/{userId}/{calendarId}")
    public ResponseEntity<Void> deleteCalendar(
            @PathVariable String userId,
            @PathVariable Long calendarId) {
        UserEntity user = new UserEntity();
        user.setUserid(userId);
        calendarService.deleteCalendar(calendarId, user);
        return ResponseEntity.noContent().build();
    }

    //유저의 모든 일정 조회
    @GetMapping("/events/{userid}")
    public ResponseEntity<List<CalendarResponse>> getEventsByUserid(@PathVariable String userid){
        log.info("Get calendars for user id {}", userid);

        List<CalendarResponse> events = calendarService.getCalendars(userid);
        return ResponseEntity.ok(events);
    }


    //유저의 선택 일정 조회
    @GetMapping("/event/{userid}/{calendarId}")
    public ResponseEntity<CalendarResponse> getEventById(
            @PathVariable String userid,
            @PathVariable Long calendarId) {
        CalendarResponse response = calendarService.getEventByIdAndUserId(calendarId, userid);
        return ResponseEntity.ok(response);
    }
}
