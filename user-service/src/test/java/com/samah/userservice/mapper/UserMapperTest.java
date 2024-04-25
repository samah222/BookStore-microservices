package com.samah.userservice.mapper;

import com.samah.userservice.dto.UserDto;
import com.samah.userservice.entity.Address;
import com.samah.userservice.entity.Privilege;
import com.samah.userservice.entity.Role;
import com.samah.userservice.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    @Autowired
    UserMapper mapper;
    User user;
    UserDto userDto;
    @BeforeEach
    void setup() {
        mapper = new UserMapper();
    }

    @Test
    @DisplayName("Should map from User to UserDto successfully")
    void shouldMapUserToUserDto() {
        user = User.builder()
                .id(1L)
                .name("Samah")
                .email("samahmahdi22@gmail.com")
                .phone("0511111111")
                .address(new Address("street1","city1","state1","1111","Country1"))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(new Role(1,"ADMIN",Set.of(new Privilege(1,"READ_WRITE"))))
                .build();
        userDto = mapper.UserToUserDto(user);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getRole(), user.getRole());
        assertEquals(userDto.getAddress(), user.getAddress());
        assertEquals(userDto.getPhone(), user.getPhone());
    }

    @Test
    @DisplayName("Should map from UserDto To User successfully")
    public void  shouldMapUserDtoToUser(){
         userDto = new UserDto(1L, "Samah" ,"samahmahdi22@gmail.com","0511111111",
                new Address("street1","city1","state1","1111","Country1"),
                new Role(1,"ADMIN", Set.of(new Privilege(1,"READ_WRITE"))));
        user = mapper.UserDtoToUser(userDto);
        assertEquals(user.getId(),userDto.getId());
        assertEquals(user.getName(),userDto.getName());
        assertEquals(user.getRole(),userDto.getRole());
        assertEquals(user.getAddress(),userDto.getAddress());
        assertEquals(user.getPhone(),userDto.getPhone());
    }
    @Test
    @DisplayName("raise NullPointerException when User Dto object is null ")
    public void should_throw_Null_Pointer_Exception_when_userDto_is_null(){
       userDto = null;
       var msg = assertThrows(NullPointerException.class, () -> mapper.UserDtoToUser(userDto));
       assertEquals(msg.getMessage(),"The User DTO should not be null");
    }

    @Test
    @DisplayName("raise NullPointerException when User object is null ")
    public void should_throw_Null_Pointer_Exception_when_user_is_null(){
        user = null;
        var msg = assertThrows(NullPointerException.class, () -> mapper.UserToUserDto(user));
        assertEquals(msg.getMessage(),"The user should not be null");
    }
}