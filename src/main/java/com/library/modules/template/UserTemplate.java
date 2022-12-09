package com.library.modules.template;

import com.library.entity.User;
import com.library.modules.user.grid.BookGrid;
import com.library.modules.user.grid.MyIssuedBooks;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

public class UserTemplate extends AppLayout {
    private Tabs tabs;

    private DrawerToggle toggle;

    private User user;
    private H4 title;
    private VerticalLayout userName;
    private VerticalLayout content;

    private Tab dashboardTab;

    private Tab recordsTab;
    private Tab issuedBooksTab;
    private Tab booksTab;
    private Button logoutButton;

    public UserTemplate(){

        user = (User) VaadinSession.getCurrent().getAttribute("user");
        initializeLayout();
    }

    private void initializeLayout() {
        userName=new VerticalLayout(VaadinIcon.USER_HEART.create(),new Label(user.getFirstName()));
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
        addToDrawer(tabs,logoutButton);
        setContent(content);
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        logoutButton =new Button("Logout",new Icon(VaadinIcon.EXIT));
        logoutButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        logoutButton.getStyle().set("color","white").set("background-color","red");
        logoutButton.addClickListener(event -> {
           logoutButton.getUI().ifPresent(ui -> ui.navigate(""));
        });
//        dashboardTab = createTab(VaadinIcon.DASHBOARD, "Dashboard");
//        recordsTab=createTab(VaadinIcon.RECORDS, "Booking History");
        issuedBooksTab = createTab(VaadinIcon.OPEN_BOOK, "My Issued Books", MyIssuedBooks.class);
        booksTab=createTab(VaadinIcon.BOOK, "Books", BookGrid.class);
//        logoutTab=createTab(VaadinIcon.ARROW_RIGHT, "Logout");
        tabs.add(booksTab,issuedBooksTab, logoutButton
//                createTab(VaadinIcon.LIST, "Tasks"),
//                createTab(VaadinIcon.CHART, "Analytics")
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
