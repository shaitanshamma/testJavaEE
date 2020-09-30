package ru.shamma.dao;


import ru.shamma.persist.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDao implements Serializable {

    private int id;

    private String login;

    private String password;

    private Set<RoleDao> roles;

    public UserDao() {
    }

    public UserDao(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = new HashSet<>();
        user.getRoles().forEach(r -> this.roles.add(new RoleDao(r)));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDao> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDao> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
