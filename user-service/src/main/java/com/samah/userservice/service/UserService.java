package com.samah.userservice.service;

import com.samah.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getUserByName(String name);
    User getUserById(Long id);
    void addUser(User user);

    void deleteUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, User user);
}
