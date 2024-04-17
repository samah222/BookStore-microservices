package com.samah.userservice.service;

import com.samah.userservice.exception.UserNotFoundException;
import com.samah.userservice.model.User;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.samah.userservice.repository.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObservationRegistry observationRegistry;

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    @Cacheable(value = "userById", key = "#id")
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow(() -> new UserNotFoundException("User not found"));
        //return user.orElse(null);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        //User dbUser = userRepository.findById(id).get(); //
        user.setId(id);
        if(!userRepository.existsById(user.getId()))
            throw new IllegalArgumentException("User does not exist");
        return userRepository.save(user);
    }
}

