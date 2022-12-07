package com.library.modules.admin.grid;

import com.library.entity.User;
import com.library.modules.template.AdminTemplate;
import com.library.mvputils.BaseView;
import com.library.service.UserService;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
@Getter
@Route(value = "user/grid",layout = AdminTemplate.class)
public class UserGridView extends BaseView<UserGridPresenter> {

    private Grid<User> userGrid;
    private Grid.Column<User> idColumn;
    private Grid.Column<User> fnameColumn;
    private Grid.Column<User> lnameColumn;
    private Grid.Column<User> emailIdColumn;
    private Grid.Column<User> contactNoColumn;
    private Grid.Column<User> joinedDateColumn;

    private TextField idFilter;
    private TextField fnameFilter;
    private TextField lnameFilter;
    private TextField emailFilter;
    private TextField contactFilter;
    private DatePicker dateFilter;
    @Override
    protected void init() {
        setSizeFull();
//            dataProvider = new ListDataProvider<>();
        userGrid = new Grid<>();
//         userGrid.setDataProvider(dataProvider);
        userGrid.setWidthFull();
//            userGridPresenter.init();
        initializeGrid();
        setFilters();
        add(userGrid);
    }
    private void initializeGrid(){
        getPresenter().init(userGrid);
        idColumn = userGrid.addColumn(User::getId).setHeader("Id");
        fnameColumn = userGrid.addColumn(User::getFirstName).setHeader("First Name");
        lnameColumn = userGrid.addColumn(User::getLastName).setHeader("Last Name");
        emailIdColumn = userGrid.addColumn(User::getEmailId).setHeader("Email ID");
        contactNoColumn = userGrid.addColumn(User::getContactNo).setHeader("Contact No");
        joinedDateColumn = userGrid.addColumn(User::getJoinedDate).setHeader("Joined Date");
    }

    private void setFilters(){
         idFilter=new TextField();
         idFilter.addValueChangeListener(event -> mainFilter());

         fnameFilter=new TextField();
         fnameFilter.addValueChangeListener(event -> mainFilter());

         lnameFilter=new TextField();
         lnameFilter.addValueChangeListener(event -> mainFilter());

         emailFilter=new TextField();
         emailFilter.setClearButtonVisible(true);
         emailFilter.addValueChangeListener(event -> mainFilter());

         contactFilter=new TextField();
         contactFilter.addValueChangeListener(event -> mainFilter());

         dateFilter=new DatePicker();
         dateFilter.addValueChangeListener(event -> mainFilter());

         idFilter.setValueChangeMode(ValueChangeMode.LAZY);
         fnameFilter.setValueChangeMode(ValueChangeMode.LAZY);
        lnameFilter.setValueChangeMode(ValueChangeMode.LAZY);
        emailFilter.setValueChangeMode(ValueChangeMode.LAZY);
        contactFilter.setValueChangeMode(ValueChangeMode.LAZY);


         HeaderRow headerRow = userGrid.appendHeaderRow();
        headerRow.getCell(idColumn).setComponent(idFilter);
        headerRow.getCell(fnameColumn).setComponent(fnameFilter);
        headerRow.getCell(lnameColumn).setComponent(lnameFilter);
        headerRow.getCell(emailIdColumn).setComponent(emailFilter);
        headerRow.getCell(contactNoColumn).setComponent(contactFilter);
        headerRow.getCell(joinedDateColumn).setComponent(dateFilter);


    }

    private void mainFilter() {
        ListDataProvider<User> dataProvider = (ListDataProvider<User>) userGrid.getDataProvider();
        dataProvider.addFilter(user -> {
            boolean b1=user.getId().equals(idFilter.getValue().equals("")?0l:Long.valueOf(idFilter.getValue()));
            boolean b2=user.getFirstName().toLowerCase().contains(fnameFilter.getValue().toLowerCase());
            boolean b3=user.getLastName().toLowerCase().contains(lnameFilter.getValue().toLowerCase());
            boolean b4=user.getEmailId().toLowerCase().startsWith(emailFilter.getValue().toLowerCase());
            boolean b5=user.getContactNo().startsWith(contactFilter.getValue());
            boolean b6=user.getJoinedDate().equals(dateFilter.getValue());

            if(dateFilter.getValue()==null || idFilter.getValue().equals("")) {
                if (dateFilter.getValue() == null && idFilter.getValue().equals("")) {
                    return b2 && b3 && b4 && b5;
                } else if (idFilter.getValue().equals("")) {
                    return b2 && b3 && b4 && b5 && b6;
                } else {
                    return b1 && b2 && b3 && b4 && b5;
                }
            }
            return b1 && b2 && b3 && b4 && b5 && b6;

        });


    }



}
