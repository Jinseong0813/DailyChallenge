package com.mysite.mylogin.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;
    


    private int points;

}
