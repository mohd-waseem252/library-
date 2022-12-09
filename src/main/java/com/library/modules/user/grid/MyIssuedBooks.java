package com.library.modules.user.grid;

import com.library.entity.Book;
import com.library.entity.BookingHistory;
import com.library.entity.User;
import com.library.modules.template.UserTemplate;
import com.library.mvputils.BaseView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.Arrays;

@SpringComponent
@UIScope
@Route(value = "user/issued/books",layout = UserTemplate.class)
public class MyIssuedBooks extends BaseView<MyIssuedBookPresenter> {

    private Grid<BookingHistory> bookingHistoryGrid;
    private Button returnButton;

    private Dialog bookDetails;
    private User user;
    @Override
    protected void init() {
        user = (User) VaadinSession.getCurrent().getAttribute("user");

        initializedGrid();
        initializeButton();
        initializeDialog();
        add(bookingHistoryGrid);
    }

    private void initializeDialog() {
        bookDetails=new Dialog(setUpDialog());
        bookDetails.setSizeFull();
        bookDetails.setDraggable(false);
        bookDetails.setModal(true);
    }

    private VerticalLayout setUpDialog() {

        Grid<Book> bookGrid=new Grid<>();
        bookGrid.setSizeFull();
        bookGrid.addColumn(Book::getBookId).setHeader("Book ID");
        bookGrid.addColumn(Book::getTitle).setHeader("Title");
        bookGrid.addColumn(Book::getAuthor).setHeader("Author");
        bookGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        bookingHistoryGrid.addSelectionListener(event -> {
            bookGrid.setItems(getPresenter().getBooks(event.getFirstSelectedItem().get()));
            bookDetails.open();
        });
        VerticalLayout verticalLayout = new VerticalLayout(returnButton, bookGrid);
        verticalLayout.setSizeFull();
        return verticalLayout;
    }

    private void initializeButton() {
        returnButton=new Button("Return Book");
        returnButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        returnButton.addClickListener(event -> {
            if(bookingHistoryGrid.getSelectedItems()!=null) {
                getPresenter().updateBookData(bookingHistoryGrid.getSelectedItems());
                bookingHistoryGrid.getDataProvider().refreshAll();
                Notification.show("Successfully Returned", 1500, Notification.Position.TOP_END);
                returnButton.getUI().ifPresent(ui -> ui.navigate("user/issued/books"));
            }
        });
    }

    private void initializedGrid() {
        bookingHistoryGrid=new Grid<>();
        bookingHistoryGrid.addColumn(BookingHistory::getId).setHeader("Booking Id");
        bookingHistoryGrid.addColumn(BookingHistory::getBookingDate).setHeader("Booking Date");
        bookingHistoryGrid.addColumn(BookingHistory::getDueDate).setHeader("Due Date");
        bookingHistoryGrid.setItems(getPresenter().getBookingData(user));
//        bookingHistoryGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        bookingHistoryGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);


    }
}
