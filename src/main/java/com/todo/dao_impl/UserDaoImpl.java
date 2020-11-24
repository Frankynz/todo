package com.todo.dao_impl;

import com.todo.dao_abstract.UserDao;
import com.todo.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public List<User> getUserByFirstName(String firstName) {
        return em.createQuery("SELECT u FROM User u WHERE u.firstName = :firstName", User.class)
                .setParameter("firstName", firstName)
                .getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getUserByUsername(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User saveUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User deleteUser(User user) {
        em.remove(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        return em.merge(user);
    }

    @Override
    public boolean isExist(User user) {
        try {
            getUserByUsername(user.getUsername());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
