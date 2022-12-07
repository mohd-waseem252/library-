package com.library.modules.admin.grid;

import com.library.mvputils.BasePresenter;
import com.library.service.BookService;
import com.library.service.UserService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class StatsPresenter extends BasePresenter<StatsView> {
    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    public int getCountOfBooks() {
        return bookService.countBooks();
    }

    public int getCountOfUsers(){
        return userService.countUsers();
    }
}
