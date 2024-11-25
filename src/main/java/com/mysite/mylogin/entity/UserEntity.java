package com.mysite.mylogin.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    

    @Id
    private String userid;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String mobile;
    
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int status = 1; // 1: 활성화, 0: 비활성화

     //포인트 컬럼 
     @Column(nullable = false)
     private int points = 0;  // 기본값을 0으로 설정


     // 기본 테마 외래 키 설정
     @ManyToOne(fetch = FetchType.LAZY)  // 지연 로딩
     @JoinColumn(name = "theme_id")  // 테마 테이블과의 외래 키
     private ThemeEntity theme;  // 기본 테마를 설정할 필드




     //포인트 추가 메서드
     public void addPoints(int points) {
        this.points += points;  // 포인트 추가
    }
    
    //포인트 감소 메서드
    public void subtractPoints(int points) {
        this.points -= points;  // 포인트 차감
    }



    // @ElementCollection(fetch = FetchType.EAGER) // 권한을 EAGER로 로딩
    // @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "userid"))
    // @Column(name = "role")
    // private Set<String> roles = new HashSet<>();  // roles 초기화
}
