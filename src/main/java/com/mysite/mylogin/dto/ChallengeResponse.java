package com.mysite.mylogin.dto;

import lombok.Data;

@Data
public class ChallengeResponse {
    private final String message;
    public ChallengeResponse(String message) {this.message = message;}
}
