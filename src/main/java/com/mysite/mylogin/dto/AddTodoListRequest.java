package com.mysite.mylogin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class AddTodoListRequest {
    @NotBlank(message = "Title은 필수 항목입니다.")
    @Size(min = 3, max = 100, message = "Title은 3자 이상, 100자 이하로 입력해 주세요.")
    private String title;

    @FutureOrPresent(message = "마감일은 미래의 날짜여야 합니다.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dueDate;

    private String notes;

    private Integer repeatType;

    private String favorite;


    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime alarm;


}
