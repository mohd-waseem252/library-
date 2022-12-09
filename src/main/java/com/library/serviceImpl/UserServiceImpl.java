package com.library.serviceImpl;

import com.library.Repository.UserRepo;
import com.library.entity.User;
import com.library.service.UserService;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public boolean userExists(User user) {
        User userByEmailId = userRepo.findByEmailId(user.getEmailId());
        if(userByEmailId!=null && userByEmailId.getPassword().equals(user.getPassword())){
            VaadinSession.getCurrent().setAttribute("user",userByEmailId);
            return true;
        }
        return false;
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

    @Override
    public List<User> findAllUser() {
        List<User> userList = userRepo.findAll();
        return userRepo.findAll();
    }

    @Override
    public int countUsers() {
        return findAllUser().size();
    }
}
