package com.mysite.mylogin.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class PointEntity {

    @Id
    private int id;
    private int points;

}
