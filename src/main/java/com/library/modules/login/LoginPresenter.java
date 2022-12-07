package com.library.modules.login;

import com.library.entity.Admin;
import com.library.entity.User;
import com.library.mvputils.BasePresenter;
import com.library.service.AdminService;
import com.library.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class LoginPresenter extends BasePresenter<MainView> {
    @Autowired
    private UserService userService;



    @Autowired
    private AdminService adminService;

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

    public boolean adminExists(String email,String password){
        Admin admin= new Admin();
        admin.setEmailId(email);
        admin.setPassword(password);

        System.out.println(userService.findAllUser());
        return adminService.adminExists(admin);


    }


}
