package com.library.service;

import com.library.entity.Book;
import com.library.entity.BookingHistory;
import com.library.entity.User;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Set;

public interface BookingOrderService {

    void saveBookingOrder(List<Book> selectedItems, User user);
    List<BookingHistory> findByUser(User user);

    void deleteSelectedBooking(List<BookingHistory> bookingHistories);

    BookingHistory findById(Long id);

}
