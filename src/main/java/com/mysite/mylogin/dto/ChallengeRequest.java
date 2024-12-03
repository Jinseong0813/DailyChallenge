package com.mysite.mylogin.dto;


import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ChallengeRequest {
    private String title;

    @FutureOrPresent(message = "마감일은 미래의 날짜여야 합니다.")
    private LocalDate start;
    @FutureOrPresent
    private LocalDate finish;
    private String status;
    private String comment;

}
