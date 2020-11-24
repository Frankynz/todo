package com.todo.controller.rest;

import com.todo.model.dto.UserTodoDto;
import com.todo.model.entity.Todo;
import com.todo.model.entity.User;
import com.todo.service_abstract.TodoService;
import com.todo.service_abstract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class TodoRestController {
    private final TodoService todoService;
    private final UserService userService;

    @Autowired
    public TodoRestController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @PostMapping("/create-todo")
    public void createTodo(@RequestBody Todo todo, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        todo.setUser(user);
        todoService.saveTodo(todo);
    }

    @PutMapping("/edit-todo/{id}")
    public void editTodo(@RequestBody Todo todo, @PathVariable("id") Long id) {
        Todo editTodo = todoService.getTodoById(id);
        editTodo.setDescription(todo.getDescription());
        todoService.updateTodo(editTodo);
    }

    @GetMapping("/get-my-active-todo")
    public List<UserTodoDto> getMyActiveTodo(Principal principal) {
        return todoService.getUserTodoDtoByUsernameAndActive(false, principal.getName());
    }

    @GetMapping("/get-my-not-active-todo")
    public List<UserTodoDto> getMyNotActiveTodo(Principal principal) {
        return todoService.getUserTodoDtoByUsernameAndActive(true, principal.getName());
    }

    @PutMapping("/set-active/{id}")
    public void setActiveTodo(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        todo.setActive(false);
        todoService.updateTodo(todo);
    }

    @PutMapping("/set-not-active/{id}")
    public void setNotActiveTodo(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        todo.setActive(true);
        todoService.updateTodo(todo);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        todoService.deleteTodo(todo);
    }
}
