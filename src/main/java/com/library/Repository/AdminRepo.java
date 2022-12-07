package com.library.Repository;

import com.library.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Long> {

    public Admin findByEmailId(String email);

}
