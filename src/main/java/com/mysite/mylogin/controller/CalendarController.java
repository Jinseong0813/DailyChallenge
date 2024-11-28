package com.mysite.mylogin.controller;


import com.mysite.mylogin.convert.CalendarConvert;
import com.mysite.mylogin.dto.CalendarRequest;
import com.mysite.mylogin.entity.CalendarEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.service.CalendarService;
import com.mysite.mylogin.service.UserupdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;
    private final UserupdateService userupdateService;

    public CalendarController(CalendarService calendarService, UserupdateService userupdateService) {
        this.calendarService = calendarService;
        this.userupdateService = userupdateService;
    }

    //모든 이벤트 조회
    @GetMapping
    public ResponseEntity<List<CalendarRequest>> getAllEvents() {
        List<CalendarRequest> events = calendarService.getAllEvents()
                .stream()
                .map(CalendarConvert::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(events);
    }


    //특정 ID로 이벤트 조회
    @GetMapping("/{id}")
    public ResponseEntity<CalendarRequest> getEventById(@PathVariable Long id) {
        return calendarService.getEventById(id)
                .map(CalendarConvert::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    //새로운 이벤트 생성
    @PostMapping
    public ResponseEntity<CalendarRequest> createEvent(@RequestBody CalendarRequest dto) {
        log.info("Creating a new calendar");
        //유저엔티티를 사용자 아이디로 조회
        UserEntity user = userupdateService.getUserById(dto.getUserID())
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 유저입니다 : " + dto.getUserID()));
        log.info("User: " + user);

        //dto -> entity로 변환 후 저장
        CalendarEntity entity = CalendarConvert.toEntity(dto, user);
        CalendarEntity savedEntity = calendarService.createEvent(entity, user.getUserid());
        log.info("Saved: " + savedEntity);


        //저장된 Entity -> dto로 변환 후 반환
        return ResponseEntity.ok(CalendarConvert.toDto(savedEntity));
    }


    //이벤트 수정/업데이트
    @PutMapping("/{id}")
    public ResponseEntity<CalendarRequest> updateEvent(@PathVariable Long id, @RequestBody CalendarRequest dto) {
        //유저엔티티를 사용자 아이디로 조회
        UserEntity user = userupdateService.getUserById(dto.getUserID())
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 유저입니다:" + dto.getUserID()));

        //DTO -> Entity로 변환
        CalendarEntity updatedEntity = CalendarConvert.toEntity(dto, user);

        //서비스 호출 후 업데이트된 Entity 반환
        CalendarEntity savedEntity = calendarService.updateEvent(id, updatedEntity, user.getUserid());

        return ResponseEntity.ok(CalendarConvert.toDto(savedEntity));
    }

    //이벤트 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        calendarService.deleteEvent(id);
        
        return ResponseEntity.noContent().build();
    }
}
