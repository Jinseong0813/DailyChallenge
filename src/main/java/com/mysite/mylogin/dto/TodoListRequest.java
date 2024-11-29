package com.mysite.mylogin.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TodoListRequest {
    private String title;

    @FutureOrPresent(message = "마감일은 미래의 날짜여야 합니다.")
    private LocalDateTime dueDate;

    private String notes;

    private Integer repeatType;

    private String favorite;

    @FutureOrPresent
    private LocalDateTime alarm;
}
