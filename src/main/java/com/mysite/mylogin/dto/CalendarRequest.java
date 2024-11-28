package com.mysite.mylogin.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CalendarRequest {

    @NotBlank
    @FutureOrPresent
    private LocalDateTime startTime; // 시작 시간

    @NotBlank
    @FutureOrPresent
    private LocalDateTime endTime; // 종료 시간

    @NotBlank
    private String title; // 제목

    private String comment; // 댓글


    private String alramStatus; // 알람 상태

    @NotBlank
    private String userID;
}
