package com.mysite.mylogin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class CalendarRequest {

    @NotNull(message = "Start time is required.")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime; // 시작 시간

    @NotNull(message = "End time is required.")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime; // 종료 시간

    @NotNull(message = "Start date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate; // 시작 날짜

    @NotNull(message = "End date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate; // 종료 날짜

    @NotBlank
    @Size(max = 20, message = "Title은 최대 20자까지 가능")
    private String title; // 제목

    @Size(max = 255, message = "Comment은 최대 255자까지 가능")
    private String comment; // 한줄평

    private String alramStatus; // 알람 여부 ("N" 또는 "Y")


}
