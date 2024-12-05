package com.mysite.mylogin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoListId;

    @Column(nullable = false)
    private String title;


    private LocalDateTime dueDate;

    private String notes;

    private Integer repeatType;

    private Integer repeatCount=0;

    private LocalDateTime alarm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
//    @JsonIgnore  // 직렬화 과정에서 user 필드를 무시하도록 추가
    private UserEntity userid;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate = LocalDate.now();  // 현재 날짜로 초기화
}
