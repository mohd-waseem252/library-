package com.library.Repository;

import com.library.entity.BookingHistory;
import com.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingOrderRepo extends JpaRepository<BookingHistory,Long> {

    List<BookingHistory> findByUser(User user);

}
