package com.library.serviceImpl;

import com.library.Repository.BookCategoryRepo;
import com.library.entity.BookCategory;
import com.library.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    BookCategoryRepo bookCategoryRepo;
    @Override
    public List<BookCategory> findAllBookCategory() {
        return bookCategoryRepo.findAll();
    }
}
