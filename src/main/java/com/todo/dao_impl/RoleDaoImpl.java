package com.todo.dao_impl;

import com.todo.dao_abstract.RoleDao;
import com.todo.model.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public Role getRoleByName(String name) {
        return em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Role> getAll() {
        return em.createQuery("SELECT r FROM Role r", Role.class)
                .getResultList();
    }

    @Override
    public Role getRoleById(Long id) {
        return em.createQuery("SELECT r FROM Role r WHERE r.id = :id", Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Role saveRole(Role role) {
        em.persist(role);
        return role;
    }

    @Override
    public Role deleteRole(Role role) {
        em.remove(role);
        return role;
    }

    @Override
    public Role updateRole(Role role) {
        return em.merge(role);
    }
}
