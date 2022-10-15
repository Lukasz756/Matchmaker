package com.projectSpringJava.Matchmaker.service;

import com.projectSpringJava.Matchmaker.DTO.UserDTO;
import com.projectSpringJava.Matchmaker.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void deleteUser(Long id);
    User findByEmail(String email);
    List<UserDTO> findAllUsers();
}
