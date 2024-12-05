package com.mysite.mylogin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
@Data
public class AddChallengeRequest {
    @NotBlank(message = "제목을 정해주세여.")
    private String title;
    private LocalDate start;
    private LocalDate finish;
    private String status;
    private String comment;

}
