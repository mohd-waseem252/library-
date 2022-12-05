package com.library.modules.login;

import com.library.entity.User;
import com.library.mvputils.BasePresenter;
import com.library.service.UserService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class LoginPresenter extends BasePresenter<MainView> {
    @Autowired
    UserService userService;

    public boolean userExists(String email,String password){
        User user=new User();
        user.setEmailId(email);
        user.setPassword(password);
        return userService.userExists(user);
    }

    public void addUser(User user){
        userService.saveUser(user);
    }
    public boolean findUserByContactNo(String contact){
        User user=new User();
        user.setContactNo(contact);
        return userService.findByContactNo(user);
    }
    public boolean findUserByEmailId(String email){
        User user=new User();
        user.setEmailId(email);
        return userService.findByEmailId(user);
    }
}
