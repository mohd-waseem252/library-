package com.library.service;

import com.library.entity.User;

import java.util.List;

public interface UserService {

    boolean userExists(User user);
    void saveUser(User user);
    boolean findByContactNo(User user);

    boolean findByEmailId(User user);

    List<User> findAllUser();

    int countUsers();
}
