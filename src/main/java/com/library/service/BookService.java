package com.library.service;

import com.library.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    int countBooks();

    Book getBookById(Long id);

    void bookQuantityMinus(Book book);

    void bookQuantityPlus(Book book);
    }
