package com.todo.controller.rest;

import com.todo.model.entity.Role;
import com.todo.model.entity.User;
import com.todo.service_abstract.RoleService;
import com.todo.service_abstract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class RegistryRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RegistryRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/registry")
    public void registryUser(@RequestBody User user) {
        if (!userService.isExist(user)) {
            user.setRoles(Collections.singleton(roleService.getRoleByName("USER")));
            userService.saveUser(user);
        }
    }
}
