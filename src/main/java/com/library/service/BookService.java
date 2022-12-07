package com.library.service;

import com.library.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    int countBooks();
    }
