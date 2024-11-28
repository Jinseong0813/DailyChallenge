package com.mysite.mylogin.controller;

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
    @PostMapping
    public ResponseEntity<TodoListEntity> addTodoItem(@RequestParam String userId, @RequestBody TodoListEntity todoListEntity) {
        TodoListEntity savedTodo = todoListService.addTodoItem(userId, todoListEntity);
        return ResponseEntity.ok(savedTodo);
    }

    // 할 일 삭제
    @DeleteMapping("/{todoListId}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable Long todoListId) {
        todoListService.deleteTodoItem(todoListId);
        return ResponseEntity.noContent().build();
    }

    // 할 일 업데이트
    @PutMapping("/{todoListId}")
    public ResponseEntity<TodoListEntity> updateTodoItem(@PathVariable Long todoListId, @RequestBody TodoListEntity updatedTodo) {
        TodoListEntity updatedEntity = todoListService.updateTodoItem(todoListId, updatedTodo);
        return ResponseEntity.ok(updatedEntity);
    }
}
