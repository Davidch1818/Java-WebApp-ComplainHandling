package com.comphand.bo.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.comphand.bo.UserBo;
import com.comphand.dao.UserDao;
import com.comphand.model.User;

/**
 * userDao is injected by applicationContext.xml. Each public method is
 * its own transaction boundary (tx:annotation-driven, configured in
 * applicationContext.xml, makes @Transactional take effect).
 */
public class UserBoImpl implements UserBo {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}
