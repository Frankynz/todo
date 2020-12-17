package com.todo.dao_abstract;

import com.todo.model.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    List<User> getUserByFirstName(String firstName);

    User getUserById(Long id);

    User getUserByUsername(String username);

    User saveUser(User user);

    User deleteUser(User user);

    User updateUser(User user);

    boolean isExist(User user);
}
