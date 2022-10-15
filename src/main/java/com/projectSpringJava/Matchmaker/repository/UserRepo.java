package com.projectSpringJava.Matchmaker.repository;

import com.projectSpringJava.Matchmaker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findById(String id);
}
