package com.library.modules.admin.grid;

import com.library.Repository.UserRepo;
import com.library.entity.User;
import com.library.mvputils.BasePresenter;
import com.library.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
@UIScope
public class UserGridPresenter  extends BasePresenter<UserGridView>{

    @Autowired
    UserService userService;

    public void init(Grid<User> userGrid){
        List<User> allUser = userService.findAllUser();
        userGrid.setItems(allUser);
    }

}
