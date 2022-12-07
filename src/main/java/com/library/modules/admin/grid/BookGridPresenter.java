package com.library.modules.admin.grid;

import com.library.entity.Book;
import com.library.entity.BookCategory;
import com.library.mvputils.BasePresenter;
import com.library.service.BookCategoryService;
import com.library.service.BookService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@UIScope
public class BookGridPresenter extends BasePresenter<BookGridView> {

    @Autowired
    BookCategoryService bookCategoryService;

    @Autowired
    BookService bookService;

    public List<BookCategory> init(){
         return bookCategoryService.findAllBookCategory();
    }

    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }
}
