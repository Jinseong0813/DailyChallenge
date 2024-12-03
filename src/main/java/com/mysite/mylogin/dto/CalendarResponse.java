package com.mysite.mylogin.dto;

import com.mysite.mylogin.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
public class CalendarResponse {
    private Long calendarId; // 자동 증가된 ID (서버가 생성 후 반환)

    private LocalDateTime startTime; // 시작 시간

    private LocalDateTime endTime; // 종료 시간

    private LocalDate startDate; // 시작 날짜

    private LocalDate endDate; // 종료 날짜

    private String title; // 제목

    private String comment; // 한줄평

    private String alramStatus; // 알람 여부 ("N" 또는 "Y")

    private String userId; // 유저 ID
}
