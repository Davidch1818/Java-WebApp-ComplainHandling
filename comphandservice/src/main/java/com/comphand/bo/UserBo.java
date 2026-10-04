package com.comphand.bo;

import java.util.List;

import com.comphand.model.User;

/**
 * Business Object contract for User.
 *
 * IMPORTANT: this exact interface (same package, same class name, same
 * method signatures) exists in BOTH the comphand and comphandservice
 * projects. That is required for Spring's HttpInvoker remoting to work:
 *   - comphandservice has UserBo + UserBoImpl (the real implementation,
 *     exported over HTTP by applicationContext.xml).
 *   - comphand has only UserBo (no impl) -- Spring generates a dynamic
 *     proxy for it at runtime that forwards every call to comphandservice.
 * If you change a method signature here, copy the change to the other
 * project's identical file too.
 */
public interface UserBo {

    List<User> listUsers();

    User getUser(Long id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);
}
