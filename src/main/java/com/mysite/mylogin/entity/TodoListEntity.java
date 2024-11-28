package com.mysite.mylogin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoListId;

    @NotBlank(message = "Title은 필수 항목입니다.")
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    private String notes;

    @NotBlank(message = "반복할려는 횟수나 일을 입력해주세요.")
    @Column(nullable = false)
    private String repeatType;

    @NotBlank(message = "Favorite은 필수 항목입니다.")
    @Column(nullable = false)
    private String favorite;

    @NotBlank(message = "Alarm은 필수 항목입니다.")
    @Column(nullable = false)
    private String alarm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore  // 직렬화 과정에서 user 필드를 무시하도록 추가
    private UserEntity userid;
}
