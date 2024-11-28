package com.mysite.mylogin.dto;


import com.mysite.mylogin.service.TodoListService;
import lombok.Data;

@Data
public class TodoListResponse {
    private final String message;
    public TodoListResponse(String message){
        this.message = message;
    }
}
