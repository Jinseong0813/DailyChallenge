package com.mysite.mylogin.service;

import com.mysite.mylogin.entity.TodoListEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.repository.TodoListRepository;
import com.mysite.mylogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoListService {

    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    // 특정 유저의 할 일 목록 조회
    public List<TodoListEntity> getTodoListForUser(String userId) {
        // userId를 통해 UserEntity 조회
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        return todoListRepository.findByUserid(user);
    }

    // 할 일 추가
    public TodoListEntity addTodoItem(String userId, TodoListEntity todoListEntity) {
        // userId를 통해 UserEntity 조회
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // 조회된 유저를 할 일에 설정하고 저장
        todoListEntity.setUserid(user);
        return todoListRepository.save(todoListEntity);
    }

    // 할 일 삭제
    public void deleteTodoItem(Long todoListId) {
        todoListRepository.deleteById(todoListId);
    }

    // 할 일 업데이트
    public TodoListEntity updateTodoItem(Long todoListId, TodoListEntity updatedTodo) {
        TodoListEntity existingTodo = todoListRepository.findById(todoListId)
                .orElseThrow(() -> new RuntimeException("Todo item not found with id: " + todoListId));

        // 수정된 정보로 기존 할 일 수정
        existingTodo.setTitle(updatedTodo.getTitle());
        existingTodo.setDueDate(updatedTodo.getDueDate());
        existingTodo.setNotes(updatedTodo.getNotes());
        existingTodo.setRepeatType(updatedTodo.getRepeatType());
        existingTodo.setFavorite(updatedTodo.getFavorite());
        existingTodo.setAlarm(updatedTodo.getAlarm());

        return todoListRepository.save(existingTodo);
    }
}
