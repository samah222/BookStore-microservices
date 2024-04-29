package com.samah.userservice.service;

import com.samah.userservice.dto.UserDto;
import com.samah.userservice.dto.UserRegistrationDto;
import com.samah.userservice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto getUserByName(String name);

    UserDto getUserById(Long id);

    UserDto addUser(UserRegistrationDto registrationDto);

    void deleteUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserDto dto);

    void saveVerificationTokenForUSer(UserDto dto, String token);

    void saveVerificationTokenForUSer(User user, String token);

    int validateVerificationToken(String token);

    String resendVerificationToken(String email);
}
