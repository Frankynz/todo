package com.todo.controller.rest;

import com.todo.model.entity.User;
import com.todo.service_abstract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistryRestController {
    private final UserService userService;

    @Autowired
    public RegistryRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registry")
    public void registryUser(@RequestBody User user) {
        if (!userService.isExist(user)) {
            userService.saveUser(user);
        }
    }
}
