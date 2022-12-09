package com.library.modules.admin.grid;

import com.library.modules.template.AdminTemplate;
import com.library.mvputils.BaseView;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.nio.charset.StandardCharsets;

@SpringComponent
@UIScope
@Route(value = "stats",layout = AdminTemplate.class)
public class StatsView extends BaseView<StatsPresenter> {
    private HorizontalLayout mainLayout;
    private VerticalLayout bookStatsLayout;
    private VerticalLayout userStatsLayout;

    @Override
    protected void init() {
        initializeLayout();
        setBookStatsLayout();
        setUserStatsLayout();
        add(mainLayout);
    }

    private void setUserStatsLayout() {
        int countOfUsers = getPresenter().getCountOfUsers();
        Label title=new Label("No of User");
        title.getStyle().set("color","black").set("font-size","30px");
        Span noOfUsers=new Span(""+countOfUsers);
        noOfUsers.getStyle().set("color","Red").set("font-size","40px").set("font-weight","bold");
        userStatsLayout.add(title,noOfUsers);
    }

    private void setBookStatsLayout() {
        int countOfBooks = getPresenter().getCountOfBooks();
        Label title=new Label("No of Books");
        title.getStyle().set("color","black").set("font-size","30px");
        Span noOfBooks=new Span(""+countOfBooks);
        noOfBooks.getStyle().set("color","Red").set("font-size","40px").set("font-weight","bold");
        bookStatsLayout.add(title,noOfBooks);
    }

    private void initializeLayout() {
        setSizeFull();
        mainLayout=new HorizontalLayout();
        mainLayout.setSizeFull();
        bookStatsLayout=new VerticalLayout();
        bookStatsLayout.setAlignItems(Alignment.CENTER);
        userStatsLayout=new VerticalLayout();
        userStatsLayout.setAlignItems(Alignment.CENTER);
        mainLayout.add(bookStatsLayout,userStatsLayout);

    }
}
