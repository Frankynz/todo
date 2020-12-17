package com.todo.service_abstract;

import com.todo.model.entity.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String name);

    List<Role> getAll();

    Role getRoleById(Long id);

    Role saveRole(Role role);

    Role deleteRole(Role role);

    Role updateRole(Role role);
}
