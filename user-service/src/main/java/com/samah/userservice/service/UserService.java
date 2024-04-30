package com.samah.userservice.service;

import com.samah.userservice.dto.*;
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

    int validateVerificationToken(String token);

    CustomResponse resendVerificationToken(String email);

    CustomResponse changePassword(ChangePasswordDto changePasswordDto);

    CustomResponse requestResetPassword(RequestResetPasswordDto requestResetPasswordDto);

    CustomResponse resetPassword(ResetPasswordDto resetPasswordDto);
}
