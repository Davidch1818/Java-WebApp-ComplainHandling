package com.comphand.dao;

import java.util.List;

import com.comphand.model.User;

/**
 * Handles requests to, and data received from, the database for User.
 * Called only by UserBoImpl -- nothing outside comphandservice ever
 * talks to a Dao directly.
 */
public interface UserDao {

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User update(User user);

    void delete(Long id);
}
