package ru.shamma.controller;


import ru.shamma.dao.RoleDao;
import ru.shamma.dao.UserDao;
import ru.shamma.service.RoleService;
import ru.shamma.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@SessionScoped
@Named
public class UserController implements Serializable {

    @EJB
    private UserService userService;

    @EJB
    private RoleService roleRepository;

    private UserDao user;

    private List<RoleDao> roles;

    private List<UserDao> users;

    public void preLoad() {
        this.roles = roleRepository.getAllRoles();
        this.users = userService.getAllUsers();
    }

    public UserDao getUser() {
        return user;
    }

    public void setUser(UserDao user) {
        this.user = user;
    }

    public List<UserDao> getAllUsers() {
        return users;
    }

    public String editUser(UserDao user) {
        this.user = user;
        return "/admin/user.xhtml?faces-redirect=true";
    }

    public void deleteUser(UserDao user) {
        userService.delete(user.getId());
    }

    public String createUser() {
        this.user = new UserDao();
        return "/admin/user.xhtml?faces-redirect=true";
    }

    public String saveUser() {
        userService.merge(this.user);
        return "/admin/users.xhtml?faces-redirect=true";
    }

    public List<RoleDao> getAllRoles() {
        return roles;
    }
}
