package com.library.modules.template;

import com.library.entity.Admin;
import com.library.modules.admin.grid.BookGridView;
import com.library.modules.admin.grid.StatsView;
import com.library.modules.admin.grid.UserGridView;
import com.library.modules.admin.home.HomeView;
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
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

import javax.annotation.PostConstruct;

public class AdminTemplate extends AppLayout {

    private Tabs tabs;

    private DrawerToggle toggle;

    private Admin admin;
    private H4 title;
    private VerticalLayout userName;
    private VerticalLayout content;

    private Tab dashboardTab;

    private Tab recordsTab;
    private Tab usersTab;
    private Tab booksTab;
    private Tab logoutTab;
    private Tab statsTab;

    public AdminTemplate(){

        admin = (Admin) VaadinSession.getCurrent().getAttribute("admin");
        initializeLayout();
    }

    private void initializeLayout() {
        userName=new VerticalLayout(VaadinIcon.USER_HEART.create(),new Label(admin.getName()));
        userName.setWidthFull();
        userName.setAlignItems(FlexComponent.Alignment.END);
        setPrimarySection(AppLayout.Section.DRAWER);
        toggle=new DrawerToggle();
        title=new H4("WELCOME");
        title.getStyle().set("margin-left","0");
        content=new VerticalLayout();
        content.setSizeFull();
        tabs=getTabs();
        addToNavbar(toggle,title,userName);
        setDrawerOpened(false);
        addToDrawer(tabs);
        setContent(content);
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        dashboardTab = createTab(VaadinIcon.DASHBOARD, "Dashboard", HomeView.class);
//        recordsTab=createTab(VaadinIcon.RECORDS, "Booking History");
        statsTab=createTab(VaadinIcon.CHART, "Analytics", StatsView.class);
        usersTab= createTab(VaadinIcon.USERS, "Users", UserGridView.class);
        booksTab=createTab(VaadinIcon.BOOK, "Books", BookGridView.class);

//        logoutTab=createTab(VaadinIcon.ARROW_RIGHT, "Logout");
        tabs.add(dashboardTab,usersTab,booksTab,statsTab
//                createTab(VaadinIcon.LIST, "Tasks"),

        );
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName,Class<? extends Component> targetClass) {
        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        // Demo has no routes
        link.setRoute(targetClass);
        link.setTabIndex(-1);

        return new Tab(link);
    }
}
