package com.todo.dao_impl;

import com.todo.dao_abstract.TodoDao;
import com.todo.model.dto.UserTodoDto;
import com.todo.model.entity.Todo;
import com.todo.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TodoDaoImpl implements TodoDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Todo> getAll() {
        return em.createQuery("SELECT t FROM Todo t", Todo.class)
                .getResultList();
    }

    @Override
    public List<Todo> getTodoByUserUsername(String username) {
        return em.createQuery("SELECT t FROM Todo t WHERE t.user.username = :username", Todo.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public List<Todo> getTodoByUser(User user) {
        return em.createQuery("SELECT t FROM Todo t WHERE t.user.id = :id", Todo.class)
                .setParameter("id", user.getId())
                .getResultList();
    }

    @Override
    public List<Todo> getTodoByUserId(Long id) {
        return em.createQuery("SELECT t FROM Todo t WHERE t.user.id = :id", Todo.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Todo> getTodoByActive(boolean isActive) {
        return em.createQuery("SELECT t FROM Todo t WHERE t.isActive = :isActive", Todo.class)
                .setParameter("isActive", isActive)
                .getResultList();
    }

    @Override
    public List<Todo> getTodoByActiveAndUserUsername(boolean isActive, String username) {
        return em.createQuery("SELECT t FROM Todo t WHERE t.user.username = :username AND t.isActive = :isActive", Todo.class)
                .setParameter("username", username)
                .setParameter("isActive", isActive)
                .getResultList();
    }

    public List<UserTodoDto> getUserTodoDtoByUsernameAndActive(boolean isActive, String username) {
        return em.createQuery("" +
                "SELECT NEW com.todo.model.dto.UserTodoDto(" +
                "t.id, t.description, t.isActive)" +
                "FROM Todo t WHERE t.user.username = :username AND t.isActive = :isActive", UserTodoDto.class)
                .setParameter("username", username)
                .setParameter("isActive", isActive)
                .getResultList();
    }

    @Override
    public Todo getTodoById(Long id) {
        return em.createQuery("SELECT t FROM Todo t WHERE t.id = :id", Todo.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Todo saveTodo(Todo todo) {
        em.persist(todo);
        return todo;
    }

    @Override
    public Todo deleteTodo(Todo todo) {
        em.remove(todo);
        return todo;
    }

    @Override
    public Todo updateTodo(Todo todo) {
        return em.merge(todo);
    }
}
