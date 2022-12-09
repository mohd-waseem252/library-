package com.library.modules.user.grid;

import com.library.entity.Book;
import com.library.entity.BookCategory;
import com.library.entity.User;
import com.library.mvputils.BasePresenter;
import com.library.service.BookCategoryService;
import com.library.service.BookService;
import com.library.service.BookingOrderService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringComponent
@UIScope
public class BookPresenter extends BasePresenter<BookGrid> {

    @Autowired
    BookCategoryService bookCategoryService;
    @Autowired
    BookService bookService;

    @Autowired
    BookingOrderService bookingOrderService;

    public List<BookCategory> getBookCategory() {
        return bookCategoryService.findAllBookCategory();
    }

    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    public void issueBook(Set<Book> selectedItems, User user){
        bookingOrderService.saveBookingOrder(new ArrayList<>(selectedItems),user);
    }
}
