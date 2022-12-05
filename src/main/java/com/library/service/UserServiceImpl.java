package com.library.service;

import com.library.Repository.UserRepo;
import com.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo userRepo;

    @Override
    public boolean userExists(User user) {
        User userByEmailId = userRepo.findByEmailId(user.getEmailId());
        return userByEmailId !=null? userByEmailId.getPassword().equals(user.getPassword()):false;
    }

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    public boolean findByContactNo(User user) {
        User userByContact = userRepo.findByContactNo(user.getContactNo());
        return userByContact!=null?true:false;
    }

    @Override
    public boolean findByEmailId(User user) {
        User userByEmail = userRepo.findByEmailId(user.getEmailId());
        return userByEmail!=null?true:false;
    }
}
