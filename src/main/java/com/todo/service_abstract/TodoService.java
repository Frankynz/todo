package com.todo.service_abstract;

import com.todo.model.dto.UserTodoDto;
import com.todo.model.entity.Todo;
import com.todo.model.entity.User;

import java.util.List;

public interface TodoService {
    List<Todo> getAll();

    List<Todo> getTodoByUserUsername(String username);

    List<Todo> getTodoByUser(User user);

    List<Todo> getTodoByUserId(Long id);

    List<Todo> getTodoByActive(boolean isActive);

    List<UserTodoDto> getUserTodoDtoByUsernameAndActive(boolean isActive, String username);

    List<Todo> getTodoByActiveAndUserUsername(boolean isActive, String username);

    Todo getTodoById(Long id);

    Todo saveTodo(Todo todo);

    Todo deleteTodo(Todo todo);

    Todo updateTodo(Todo todo);
}
