package com.mysite.mylogin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ChallengeEntity {
    @Id
    @Column(name = "challenge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate start;

    @Column(nullable = false)
    private LocalDate finish;

    @Column(nullable = false)
    private String status;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userid;
}
