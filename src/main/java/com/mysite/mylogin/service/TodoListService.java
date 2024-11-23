package com.mysite.mylogin.service;


import com.mysite.mylogin.entity.TodoListEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoListService {
    private final TodoListRepository todoListRepository;

    //  todolist 조회
    public List<TodoListEntity> getTodoListRepository(String userid) {
        return todoListRepository.findUserid(userid);
    }
    // todolist  다 조회
//    public  List<TodoListEntity> getAllTodoListRepository(){ return todoListRepository.findAll();}

    //   todolist 저장
    public TodoListEntity addTodoItem(TodoListEntity todoListEntity) {
        return todoListRepository.save(todoListEntity);
    }

    //    toodlist 삭제
    public void deleteTodoItem(Long todoListId) {
        todoListRepository.deleteById(todoListId);
    }

    //    todolist 업뎃
    public TodoListEntity updateTodoItem(Long todoListId, TodoListEntity updatedTodo) {
        TodoListEntity existingTodo = todoListRepository.findById(todoListId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 할 일을 찾을 수 없습니다. ID: " + todoListId));

        // 수정된 정보로 업데이트
        existingTodo.setTitle(updatedTodo.getTitle());
        existingTodo.setDue_date(updatedTodo.getDue_date());
        existingTodo.setNotes(updatedTodo.getNotes());
        existingTodo.setRepeat_type(updatedTodo.getRepeat_type());
        existingTodo.setFavorite(updatedTodo.getFavorite());
        existingTodo.setAlarm(updatedTodo.getAlarm());

        return todoListRepository.save(existingTodo);
    }
}
