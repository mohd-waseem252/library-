package com.library.service;

import com.library.entity.User;

public interface UserService {

    boolean userExists(User user);
    void saveUser(User user);
    boolean findByContactNo(User user);

    boolean findByEmailId(User user);
}
