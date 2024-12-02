package com.mysite.mylogin.service;

import com.mysite.mylogin.dto.AddTodoListRequest;
import com.mysite.mylogin.dto.TodoListRequest;
import com.mysite.mylogin.dto.TodoListResponse;
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
    public TodoListResponse addTodoItem(String userId, AddTodoListRequest addTodoListRequest) {
        // userId를 통해 UserEntity 조회
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        TodoListEntity todoListEntity = new TodoListEntity();
        todoListEntity.setUserid(user);
        todoListEntity.setTitle(addTodoListRequest.getTitle());
        todoListEntity.setNotes(addTodoListRequest.getNotes());
        todoListEntity.setFavorite(addTodoListRequest.getFavorite());
        todoListEntity.setAlarm(addTodoListRequest.getAlarm());
        todoListEntity.setDueDate(addTodoListRequest.getDueDate());
        todoListEntity.setRepeatType(addTodoListRequest.getRepeatType());
        todoListRepository.save(todoListEntity);
        return new TodoListResponse("추가되었습니다.");
    }

    // 할 일 삭제
    public TodoListResponse deleteTodoItem(Long todoListId) {
        todoListRepository.deleteById(todoListId);
        return new TodoListResponse("삭제되었습니다,");
    }

    // 할 일 업데이트
    public TodoListResponse updateTodoItem(Long todoListId, TodoListRequest updatedTodo) {
        TodoListEntity existingTodo = todoListRepository.findById(todoListId)
                .orElseThrow(() -> new RuntimeException("해당 아이디를 찾을 수가 없습니다.: " + todoListId));

        // 수정된 정보로 기존 할 일 수정
        if (updatedTodo.getTitle() != null) {
            existingTodo.setTitle(updatedTodo.getTitle());
        }
        if (updatedTodo.getDueDate() != null) {
            existingTodo.setDueDate(updatedTodo.getDueDate());
        }
        if (updatedTodo.getNotes() != null) {
            existingTodo.setNotes(updatedTodo.getNotes());
        }
        if (updatedTodo.getRepeatType() != null) {
            existingTodo.setRepeatType(updatedTodo.getRepeatType());
        }
        if (updatedTodo.getFavorite() != null) {
            existingTodo.setFavorite(updatedTodo.getFavorite());
        }
        if (updatedTodo.getAlarm() != null) {
            existingTodo.setAlarm(updatedTodo.getAlarm());
        }
        todoListRepository.save(existingTodo);
        return new TodoListResponse("변경이 완료되었습니다.");

    }
}
