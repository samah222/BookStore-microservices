package com.samah.userservice.mapper;

import com.samah.userservice.dto.UserDto;
import com.samah.userservice.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User UserDtoToUser(UserDto dto){
        if(dto == null){
            throw new NullPointerException("The User DTO should not be null");
        }
        User user = new User();
        user.setId(dto.id());
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setAddress(dto.address());
        user.setPhone(dto.phone());
        user.setRole(dto.role());
        return user;
    }

    public UserDto UserToUserDto(User user){
        UserDto userDto = new UserDto(user.getId(),user.getName(),user.getEmail(),
                user.getPhone(), user.getAddress(), user.getRole() );
        return userDto;
    }

}
