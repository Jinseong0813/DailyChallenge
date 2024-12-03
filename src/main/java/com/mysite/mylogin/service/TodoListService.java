package com.mysite.mylogin.service;

import com.mysite.mylogin.dto.AddTodoListRequest;
import com.mysite.mylogin.dto.TodoListRequest;
import com.mysite.mylogin.dto.TodoListResponse;
import com.mysite.mylogin.entity.TodoListEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.repository.TodoListRepository;
import com.mysite.mylogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    // 새로 추가할 할일 제목
    String newTitle = addTodoListRequest.getTitle();

    // 기존 제목과 동일한 할일 목록을 찾기
    List<TodoListEntity> existingTodos = todoListRepository.findByUserid(user); // 같은 유저의 할일 목록 조회

    // 동일한 제목을 가진 할일 중 가장 작은 todo_list_id를 가진 할일 찾기
    TodoListEntity matchingTodo = null;
    for (TodoListEntity todo : existingTodos) {
        if (todo.getTitle().equalsIgnoreCase(newTitle)) {
            // todo_list_id가 가장 작은 할일을 찾음
            if (matchingTodo == null || todo.getTodoListId() < matchingTodo.getTodoListId()) {
                matchingTodo = todo;
            }
        }
    }

    TodoListEntity todoListEntity = new TodoListEntity();
    todoListEntity.setUserid(user);
    todoListEntity.setTitle(newTitle);
    todoListEntity.setNotes(addTodoListRequest.getNotes());
    todoListEntity.setAlarm(addTodoListRequest.getAlarm());
    todoListEntity.setDueDate(addTodoListRequest.getDueDate());
    todoListEntity.setRepeatType(addTodoListRequest.getRepeatType());

    // 만약 기존 제목이 같거나 비슷한 할일이 존재하면
    if (matchingTodo != null) {
        // 가장 작은 todo_list_id를 가진 기존 할일의 repeatCount를 1 증가시키기
        matchingTodo.setRepeatCount(matchingTodo.getRepeatCount() + 1);
        todoListRepository.save(matchingTodo); // 기존 할일의 repeatCount를 갱신


    } else {
        // 새로운 할일은 기본 repeatCount 값으로 설정 (기본값은 1)
        todoListEntity.setRepeatCount(1);
    }

    // 할일 저장
    TodoListEntity savedTodo = todoListRepository.save(todoListEntity);

    // `repeat_type`이 null이 아닌 경우 반복 추가 실행
    if (addTodoListRequest.getRepeatType() != null) {
        handleRepeatTodoItem(savedTodo.getTodoListId());
    }

    return new TodoListResponse("추가되었습니다 add complete.");
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
    
        if (updatedTodo.getAlarm() != null) {
            existingTodo.setAlarm(updatedTodo.getAlarm());
        }
        todoListRepository.save(existingTodo);
        return new TodoListResponse("변경이 완료되었습니다.");

    }


    // 할일 반복 추가
    // 할일 반복 추가
    public TodoListResponse handleRepeatTodoItem(Long todoListId) {
    // todoListId로 TodoListEntity 조회
    TodoListEntity todoList = todoListRepository.findById(todoListId)
            .orElseThrow(() -> new RuntimeException("TodoList not found with id: " + todoListId));

    int repeatInterval = todoList.getRepeatType();  // repeatType에 맞는 반복 주기 (예: 2일마다, 1일마다)
    
    if (repeatInterval < 1 || repeatInterval > 30) {
        throw new IllegalArgumentException("유효하지 않은 반복 주기입니다.");
    }
    
    LocalDate startDate = todoList.getCreatedDate();  // 최초 할 일의 시작 날짜
    int repeatCount = 30 / repeatInterval;  // 30일 기준으로 반복 횟수 계산
    
    // 최초 할 일에 반복 횟수 저장
    todoList.setRepeatCount(repeatCount);
    todoListRepository.save(todoList);  // DB에 저장

    // 반복되는 할 일 처리
    for (int i = 1; i <= repeatCount; i++) {
        LocalDate repeatDate = startDate.plusDays(i * repeatInterval); 
        
        TodoListEntity newTodo = new TodoListEntity();
        newTodo.setTitle(todoList.getTitle());
        newTodo.setNotes(todoList.getNotes());
        newTodo.setRepeatType(todoList.getRepeatType());
        newTodo.setAlarm(todoList.getAlarm());
        newTodo.setDueDate(todoList.getDueDate());
        newTodo.setUserid(todoList.getUserid());  // 동일한 사용자
        newTodo.setCreatedDate(repeatDate);  // 생성날짜 

        // 새로운 할 일을 생성하여 DB에 저장
        todoListRepository.save(newTodo);
    }

    // 반복 추가 완료 메시지 반환 (title과 repeatType 포함)
    return new TodoListResponse(
        String.format("할일 '%s'의 %d일 주기 반복이 성공적으로 설정되었습니다. repeat complete", 
                      todoList.getTitle(), todoList.getRepeatType())
    );
   }




//한달 간 TOP 5
// 서비스에서 수정된 코드
public TodoListResponse getTopUsedTodos(String userId) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime oneMonthAgo = now.minusMonths(1);

    // 쿼리 실행 (결과는 Object[] 타입)
    List<Object[]> results = todoListRepository.findTopUsedTodos(userId, oneMonthAgo, now);

    // 상위 5개 할 일을 채우기 위한 리스트 초기화
    List<String> topDetails = new ArrayList<>();

    if (results != null && !results.isEmpty()) {
        // 결과가 있을 경우, 최대 5개까지 추가
        topDetails = results.stream()
                            .limit(5)
                            .map(result -> "제목: " + result[0] +  // 제목 (title)
                                          ", 반복 횟수 합: " + result[1])  // 반복 횟수 합 (totalRepeatCount)
                            .collect(Collectors.toList());
    }

    // 부족한 데이터 채우기
    int missingCount = 5 - topDetails.size();
    for (int i = 0; i < missingCount; i++) {
        topDetails.add("(없음)");  // 빈 슬롯 채우기
    }

    return new TodoListResponse("가장 많이 사용된 할 일 TOP 5: " + String.join(", ", topDetails));
}







}
