package com.mysite.mylogin.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TodoListRequest {
    private String title;

    private LocalDateTime dueDate;

    private String notes;

    private Integer repeatType;


    private LocalDateTime alarm;
}
