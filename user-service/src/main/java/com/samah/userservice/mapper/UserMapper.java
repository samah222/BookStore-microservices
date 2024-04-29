package com.samah.userservice.mapper;

import com.samah.userservice.dto.UserDto;
import com.samah.userservice.dto.UserRegistrationDto;
import com.samah.userservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public User UserDtoToUser(UserDto dto) {
        if (dto == null) {
            throw new NullPointerException("The User DTO should not be null");
        }
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAddress(dto.getAddress());
        user.setPhone(dto.getPhone());
        user.setRole(dto.getRole());
        return user;
    }

    public UserDto UserToUserDto(User user) {
        if (user == null)
            throw new NullPointerException("The user should not be null");
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail(),
                user.getPhone(), user.getAddress(), user.getRole());
        return userDto;
    }

    public User UserRegistrationDtoToUser(UserRegistrationDto registrationDto) {
        if (registrationDto == null) {
            throw new NullPointerException("The User Registration DTO should not be null");
        }
        User user = new User();
        user.setPassword(registrationDto.getPassword());
        user.setName(registrationDto.getName());
        user.setEmail(registrationDto.getEmail());
        user.setAddress(registrationDto.getAddress());
        user.setPhone(registrationDto.getPhone());
        user.setRole(registrationDto.getRole());
        return user;
    }


}
