package com.mysite.mylogin.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ChallengeRequest {
    private String title;
    private LocalDate start;
    private LocalDate finish;
    private String status;
    private String comment;

}
