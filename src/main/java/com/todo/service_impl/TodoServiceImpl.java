package com.todo.service_impl;

import com.todo.dao_abstract.TodoDao;
import com.todo.model.dto.UserTodoDto;
import com.todo.model.entity.Todo;
import com.todo.model.entity.User;
import com.todo.service_abstract.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
    private final TodoDao todoDao;

    @Autowired
    public TodoServiceImpl(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    public List<Todo> getAll() {
        return todoDao.getAll();
    }

    @Override
    public List<Todo> getTodoByUserUsername(String username) {
        return todoDao.getTodoByUserUsername(username);
    }

    @Override
    public List<Todo> getTodoByUser(User user) {
        return todoDao.getTodoByUser(user);
    }

    @Override
    public List<Todo> getTodoByUserId(Long id) {
        return todoDao.getTodoByUserId(id);
    }

    @Override
    public List<Todo> getTodoByActive(boolean isActive) {
        return todoDao.getTodoByActive(isActive);
    }

    @Override
    public List<UserTodoDto> getUserTodoDtoByUsernameAndActive(boolean isActive, String username) {
        return todoDao.getUserTodoDtoByUsernameAndActive(isActive, username);
    }

    @Override
    public List<Todo> getTodoByActiveAndUserUsername(boolean isActive, String username) {
        return todoDao.getTodoByActiveAndUserUsername(isActive, username);
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoDao.getTodoById(id);
    }

    @Override
    public Todo saveTodo(Todo todo) {
        return todoDao.saveTodo(todo);
    }

    @Override
    public Todo deleteTodo(Todo todo) {
        return todoDao.deleteTodo(todo);
    }

    @Override
    public Todo updateTodo(Todo todo) {
        return todoDao.updateTodo(todo);
    }
}
