package com.library.modules.admin.home;

import com.library.Repository.UserRepo;
import com.library.entity.Admin;
import com.library.modules.admin.grid.UserGridView;
import com.library.modules.template.AdminTemplate;
import com.library.mvputils.BaseView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
@Route(value="home",layout = AdminTemplate.class)
public class HomeView extends BaseView<HomePresenter>{

//    @Autowired
//    HomePresenter homePresenter;

    @Autowired
    UserRepo userRepo;
    @Override
    protected void init() {
        int size = userRepo.findAll().size();

        add(new Label("size is " + size));

//       admin = (Admin) VaadinSession.getCurrent().getAttribute("admin");
    }

}
