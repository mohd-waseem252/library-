package com.library.serviceImpl;

import com.library.Repository.BookRepo;
import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepo bookRepo;

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public int countBooks() {
        return getAllBooks().size();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepo.findById(id).orElse(null);
    }

    @Override
    public void bookQuantityMinus(Book book) {
        Book bookById = getBookById(book.getBookId());
        if(bookById!=null){
            bookById.setCopiesAvailable(bookById.getCopiesAvailable()-1l);
            bookRepo.save(bookById);
        }

    }

    @Override
    public void bookQuantityPlus(Book book) {
        Book bookById = getBookById(book.getBookId());
        if(bookById!=null){
            bookById.setCopiesAvailable(bookById.getCopiesAvailable()+1l);
            bookRepo.save(bookById);
        }
    }

}
