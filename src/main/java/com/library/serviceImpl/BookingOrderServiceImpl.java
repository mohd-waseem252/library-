package com.library.serviceImpl;

import com.library.Repository.BookingOrderRepo;
import com.library.entity.Book;
import com.library.entity.BookingHistory;
import com.library.entity.User;
import com.library.service.BookingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingOrderServiceImpl implements BookingOrderService {

    @Autowired
    BookingOrderRepo bookingOrderRepo;
    @Autowired
    BookServiceImpl bookService;
    @Override
    public void saveBookingOrder(List<Book> selectedItems, User user) {
        BookingHistory bookingOrder=new BookingHistory();
        bookingOrder.setBooks(selectedItems);
        bookingOrder.setUser(user);
        bookingOrder.setBookingDate(LocalDate.now());
        bookingOrder.setDueDate(LocalDate.now().plusDays(7));
        List<BookingHistory> bookingOrders=new ArrayList<>();
        bookingOrders.add(bookingOrder);
//        selectedItems.stream().forEach(book -> book.setBookingHistory(bookingOrders));
        selectedItems.stream().forEach(book -> bookService.bookQuantityMinus(book));
        bookingOrderRepo.saveAll(bookingOrders);

    }

    @Override
    public List<BookingHistory> findByUser(User user) {
        return bookingOrderRepo.findByUser(user);
    }

    @Override
    public void deleteSelectedBooking(List<BookingHistory> bookingHistories) {
        for (BookingHistory booking :bookingHistories) {
            booking.getBooks().stream().forEach(book -> bookService.bookQuantityPlus(book));
        }
        bookingOrderRepo.deleteAll(bookingHistories);
    }

    @Override
    public BookingHistory findById(Long id) {
        return bookingOrderRepo.findById(id).orElse(null);
    }
}
