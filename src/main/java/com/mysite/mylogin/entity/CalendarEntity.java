package com.mysite.mylogin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CalendarEntity {
    @Id
    @Column(name = "calendar_id")
    private int calendarId;

    private LocalDate date;
    private String DdayName;
    private String comment;
}
