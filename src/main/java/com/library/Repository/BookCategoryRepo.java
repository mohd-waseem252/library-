package com.library.Repository;

import com.library.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepo extends JpaRepository<BookCategory, Long> {
}
