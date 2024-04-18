package com.samah.userservice.mapper;

import com.samah.userservice.dto.UserDto;
import com.samah.userservice.entity.Address;
import com.samah.userservice.entity.Privilege;
import com.samah.userservice.entity.Role;
import com.samah.userservice.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    @Autowired
    UserMapper mapper;
    User user;
    UserDto userDto;

    @Test
    void shouldMapUserToUserDto() {
        mapper = new UserMapper();
        User user = User.builder()
                .id(1L)
                .name("Samah")
                .email("samahmahdi22@gmail.com")
                .phone("0511111111")
                .address(new Address("street1","city1","state1","1111","Country1"))
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .role(new Role(1,"ADMIN",Set.of(new Privilege(1,"READ_WRITE"))))
                .build();
        userDto = mapper.UserToUserDto(user);
        assertEquals(userDto.id(), user.getId());
        assertEquals(userDto.name(), user.getName());
        assertEquals(userDto.role(), user.getRole());
        assertEquals(userDto.address(), user.getAddress());
        assertEquals(userDto.phone(), user.getPhone());
    }

    @Test
    public void  shouldMapUserDtoToUser(){
        mapper = new UserMapper();
        UserDto userDto = new UserDto(1L, "Samah" ,"samahmahdi22@gmail.com","0511111111",
                new Address("street1","city1","state1","1111","Country1"),
                new Role(1,"ADMIN", Set.of(new Privilege(1,"READ_WRITE"))));
        user = mapper.UserDtoToUser(userDto);
        assertEquals(user.getId(),userDto.id());
        assertEquals(user.getName(),userDto.name());
        assertEquals(user.getRole(),userDto.role());
        assertEquals(user.getAddress(),userDto.address());
        assertEquals(user.getPhone(),userDto.phone());
    }
    @Test
    public void should_throw_Null_Pointer_Exception_when_userDto_is_null(){
       mapper = new UserMapper();
       userDto = null;
       var msg = assertThrows(NullPointerException.class, () -> mapper.UserDtoToUser(userDto));
       assertEquals(msg.getMessage(),"The User DTO should not be null");
    }
}