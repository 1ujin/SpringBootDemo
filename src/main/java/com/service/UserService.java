package com.service;

import com.pojo.User;

import java.util.Collection;

public interface UserService {
    public void createUser(User user);
    public void updateUser(Long id, User user);
    public void deleteUser(Long id);
    public User getUser(Long id);
    public Collection<User> getUsers();
}
