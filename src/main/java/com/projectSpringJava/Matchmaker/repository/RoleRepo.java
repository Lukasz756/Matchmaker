package com.projectSpringJava.Matchmaker.repository;

import com.projectSpringJava.Matchmaker.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
