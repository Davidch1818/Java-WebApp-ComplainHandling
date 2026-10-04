package com.comphand.viewmodel;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.comphand.bo.UserBo;
import com.comphand.model.User;

/**
 * ZK MVVM ViewModel bound to index.zul.
 *
 * This class ONLY handles input coming from the page (button clicks,
 * textbox values) and output shown back to the user (the users list,
 * status messages). It never talks to the database directly.
 *
 * userBo is not created with "new" -- it's a Spring bean (see
 * applicationContext.xml, bean id "userBo") resolved into this field
 * by ZK's DelegatingVariableResolver (configured in zk.xml). At
 * runtime it is a dynamic proxy that forwards every call to
 * comphandservice over HTTP.
 */
public class UserViewModel {

    @WireVariable
    private UserBo userBo;

    private List<User> users;
    private User newUser = new User();
    private String statusMessage = "";

    @Init
    public void init() {
        loadUsers();
    }

    private void loadUsers() {
        users = userBo.listUsers();
    }

    public List<User> getUsers() {
        return users;
    }

    public User getNewUser() {
        return newUser;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    @Command
    @NotifyChange({"users", "newUser", "statusMessage"})
    public void createUser() {
        try {
            userBo.createUser(newUser);
            statusMessage = "User created.";
            newUser = new User();
            loadUsers();
        } catch (Exception e) {
            statusMessage = "Could not create user: " + e.getMessage();
        }
    }

    @Command
    @NotifyChange({"users", "statusMessage"})
    public void deleteUser(@BindingParam("user") User user) {
        try {
            userBo.deleteUser(user.getId());
            statusMessage = "User deleted.";
            loadUsers();
        } catch (Exception e) {
            statusMessage = "Could not delete user: " + e.getMessage();
        }
    }
}
