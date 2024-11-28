package com.mysite.mylogin.controller;

import com.mysite.mylogin.dto.AddTodoListRequest;
import com.mysite.mylogin.dto.TodoListRequest;
import com.mysite.mylogin.dto.TodoListResponse;
import com.mysite.mylogin.entity.TodoListEntity;
import com.mysite.mylogin.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todolist")
public class TodoListController {

    private final TodoListService todoListService;

    // 특정 유저의 할 일 목록 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<TodoListEntity>> getTodoListRepository(@PathVariable String userId) {
        List<TodoListEntity> todoList = todoListService.getTodoListForUser(userId);
        return ResponseEntity.ok(todoList);
    }

    // 할 일 추가
    @PostMapping("/add/{userId}")
    public ResponseEntity<TodoListResponse> addTodoItem(@PathVariable String userId, @RequestBody AddTodoListRequest addTodoListRequest) {
        TodoListResponse savedTodo = todoListService.addTodoItem(userId, addTodoListRequest);
        return ResponseEntity.ok(savedTodo);
    }

    // 할 일 삭제
    @DeleteMapping("/delete/{todoListId}")
    public ResponseEntity<TodoListResponse> deleteTodoItem(@PathVariable Long todoListId) {
        TodoListResponse delete = todoListService.deleteTodoItem(todoListId);
        ResponseEntity.noContent().build();
        return ResponseEntity.ok(delete);
    }

    // 할 일 업데이트
    @PatchMapping("/update/{todoListId}")
    public ResponseEntity<TodoListResponse> updateTodoItem(@PathVariable Long todoListId, @RequestBody TodoListRequest updatedTodo) {
        TodoListResponse updatedEntity = todoListService.updateTodoItem(todoListId, updatedTodo);
        return ResponseEntity.ok(updatedEntity);
    }
}
