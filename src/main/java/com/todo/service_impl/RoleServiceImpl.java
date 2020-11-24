package com.todo.service_impl;

import com.todo.dao_abstract.RoleDao;
import com.todo.model.entity.Role;
import com.todo.service_abstract.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public Role saveRole(Role role) {
        return roleDao.saveRole(role);
    }

    @Override
    public Role deleteRole(Role role) {
        return roleDao.deleteRole(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleDao.updateRole(role);
    }
}
