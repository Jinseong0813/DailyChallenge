package com.mysite.mylogin.controller;


import com.mysite.mylogin.entity.TodoListEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.service.LoginService;
import com.mysite.mylogin.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todolist")
public class TodoListController {
    private final TodoListService todoListService;


//    유저 조회
    @GetMapping("/{userid}")
    public ResponseEntity<List<TodoListEntity>> getTodoListRepository(@PathVariable String userid){
        List<TodoListEntity> todoList = todoListService.getTodoListRepository(userid);
        return ResponseEntity.ok(todoList);
    }
//    할일 조회
//    @GetMapping
//    public ResponseEntity<List<TodoListEntity>> getAllTodoListRepository(){ return ResponseEntity.ok(todoListService.getAllTodoListRepository());}

// 할일 추가
    @PostMapping
    public ResponseEntity<TodoListEntity> addTodoItem(@RequestBody TodoListEntity todoListEntity){
        TodoListEntity saveTodo = todoListService.addTodoItem(todoListEntity);
        return ResponseEntity.ok(saveTodo);
    }
//    할일 삭제
    @DeleteMapping("/{todoListId}")
    public  ResponseEntity<Void> deleteTodoItem(@PathVariable Long todoListId){
        todoListService.deleteTodoItem(todoListId);
        return ResponseEntity.noContent().build();
    }
//   할일 업뎃
    @PutMapping("/{todoListId}")
    public ResponseEntity<TodoListEntity>  updateTodoItem(@PathVariable Long todoListId , @RequestBody TodoListEntity updatedTodo){
        TodoListEntity updateTodoItem = todoListService.updateTodoItem(todoListId,updatedTodo);
        return ResponseEntity.ok(updateTodoItem);
    }
}
