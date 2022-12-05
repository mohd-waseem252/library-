package com.library.Repository;

import com.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    public User findByEmailId(String email);

    public User findByContactNo(String contact);
}
