package com.mysite.mylogin.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Data
public class AddChallengeRequest {
    @NotBlank(message = "제목을 정해주세여.")
    @Size(min = 3, max = 100, message = "제목은 3자 이상, 100자 이하로 입력해 주세요.")
    private String title;

    @FutureOrPresent(message = "시작일은 오늘 또는 미래의 날짜여야 합니다.")
    private LocalDate start;

    private LocalDate finish;

    private boolean isDailyChallenge;

    // 종료일은 시작일 이후여야 함 (일일 챌린지인 경우 예외 처리)
    @AssertTrue(message = "종료일은 시작일보다 늦어야 합니다.")
    public boolean isFinishAfterStart() {
        if (finish == null || start == null) {
            return true; // 하나라도 null이면 검증을 하지 않음
        }

        // 일일 챌린지라면 종료일이 시작일과 같을 수 있음
        if (isDailyChallenge) {
            return !finish.isBefore(start); // 종료일이 시작일 이전이면 안 됨
        }

        // 일일 챌린지가 아니라면 종료일이 시작일보다 뒤여야 함
        return finish.isAfter(start);
    }

    private String status;


    private String comment;

}
