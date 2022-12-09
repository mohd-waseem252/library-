package com.library.modules.user.grid;

import com.library.entity.Book;
import com.library.entity.BookingHistory;
import com.library.entity.User;
import com.library.mvputils.BasePresenter;
import com.library.service.BookingOrderService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@SpringComponent
@UIScope
public class MyIssuedBookPresenter extends BasePresenter<MyIssuedBooks> {

    @Autowired
    BookingOrderService bookingOrderService;

    public List<BookingHistory> getBookingData(User user) {
        return bookingOrderService.findByUser(user);
    }
    public void updateBookData(Set<BookingHistory> selectedItems){
        bookingOrderService.deleteSelectedBooking(new ArrayList<>(selectedItems));
    }

    public List<Book> getBooks(BookingHistory bookingHistory){
        return bookingHistory.getBooks();
    }
}
