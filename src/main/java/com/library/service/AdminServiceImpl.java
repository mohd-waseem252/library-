package com.library.service;

import com.library.Repository.AdminRepo;
import com.library.entity.Admin;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepo adminRepo;

    @Override
    public boolean adminExists(Admin admin) {
        Admin adminByEmail = adminRepo.findByEmailId(admin.getEmailId());
        if(adminByEmail!=null && adminByEmail.getPassword().equals(admin.getPassword())==true){
            VaadinSession.getCurrent().setAttribute("admin",adminByEmail);
            return true;
        }
        return false;

    }
}
