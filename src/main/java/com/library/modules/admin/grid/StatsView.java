package com.library.modules.admin.grid;

import com.library.modules.template.AdminTemplate;
import com.library.mvputils.BaseView;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
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
        Label noOfUsers=new Label("No of User");
        noOfUsers.getStyle().set("color","black").set("font-weight","bold");
        Button countButton=new Button();
        countButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
        countButton.getStyle().set("color","red").set("background-color","grey");
        countButton.setWidth(40, Unit.PERCENTAGE);
        countButton.setHeight(40,Unit.PERCENTAGE);
        countButton.setText(""+countOfUsers+"");
        userStatsLayout.add(noOfUsers,countButton);
    }

    private void setBookStatsLayout() {
        int countOfBooks = getPresenter().getCountOfBooks();
        Label noOfBooks=new Label("No of Books");
        noOfBooks.getStyle().set("color","black").set("font-weight","bold");
        Button countButton=new Button();
        countButton.getStyle().set("color","red").set("background-color","grey");
        countButton.setWidth(40, Unit.PERCENTAGE);
        countButton.setHeight(40,Unit.PERCENTAGE);
        countButton.setText(""+countOfBooks+"");
        bookStatsLayout.add(noOfBooks,countButton);
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
