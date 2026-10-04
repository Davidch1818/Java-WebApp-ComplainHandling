package com.comphand.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.comphand.dao.UserDao;
import com.comphand.model.User;

/**
 * sessionFactory is injected by applicationContext.xml. No manual
 * transaction handling here -- getCurrentSession() returns the Session
 * bound to whatever Spring transaction is already open, and that
 * transaction is started/committed by @Transactional on UserBoImpl.
 */
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<User> findAll() {
        return currentSession().createQuery("from User", User.class).list();
    }

    @Override
    public User findById(Long id) {
        return currentSession().get(User.class, id);
    }

    @Override
    public User save(User user) {
        currentSession().persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        return (User) currentSession().merge(user);
    }

    @Override
    public void delete(Long id) {
        User user = currentSession().get(User.class, id);
        if (user != null) {
            currentSession().remove(user);
        }
    }
}
