package com.samah.userservice.service;

import com.samah.userservice.dto.UserDto;
import com.samah.userservice.entity.Address;
import com.samah.userservice.entity.Privilege;
import com.samah.userservice.entity.Role;
import com.samah.userservice.entity.User;
import com.samah.userservice.mapper.UserMapper;
import com.samah.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImpTest {
    @InjectMocks
    private UserServiceImp userServiceImp;
    @Mock
   private UserRepository userRepository;
    @Mock
    private UserMapper mapper;

//    UserRepository userRepository = mock(UserRepository.class);
//    UserMapper mapper = mock(UserMapper.class);

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
//        PcUserService mock = org.mockito.Mockito.mock(PcUserService.class);
//        when(mock.read("1")).thenReturn(pcUser);
        User mockUSer = org.mockito.Mockito.mock(User.class);

    }
    @Test
    public void when_add_user_should_be_saved_successfully(){
        //given
        User user = new User(1L, "Samah", "123456789", "samahmahdi22@gmail.com",
                "0511111111", new Address("street1","city1","state1","1111",
                "Country1"),LocalDate.now(), LocalDate.now(), new Role(1,"ADMIN",
                Set.of(new Privilege(1,"READ_WRITE"))));

        UserDto userDto = new UserDto(1L, "Samah", "samahmahdi22@gmail.com","0511111111",
                new Address("street1","city1","state1","1111","Country1"),
                new Role(1,"ADMIN", Set.of(new Privilege(1,"READ_WRITE"))) ) ;

        //when
        when(mapper.UserToUserDto(user)).thenReturn(userDto);
        when(userRepository.save(user)).thenReturn(user);
        UserDto savedUserDto = userServiceImp.addUser(user);

       //then
       assertEquals(savedUserDto.id(),userDto.id());
        assertEquals(savedUserDto.name(),userDto.name());
        assertEquals(savedUserDto.role(),userDto.role());
        assertEquals(savedUserDto.address(),userDto.address());
        assertEquals(savedUserDto.phone(),userDto.phone());
        assertEquals(savedUserDto.email(),userDto.email());
        verify(mapper,times(0)).UserDtoToUser(userDto);
        verify(userRepository,times(1)).save(user);
    }
    @Test
    public void should_get_all_users_successfully(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Samah", "123456789", "samahmahdi22@gmail.com",
                "0511111111", new Address("street1","city1","state1","1111",
                "Country1"),LocalDate.now(), LocalDate.now(), new Role(1,"ADMIN",
                Set.of(new Privilege(1,"READ_WRITE")))));

        when(userRepository.findAll()).thenReturn(userList);
        when(mapper.UserToUserDto(any(User.class))).thenReturn(new UserDto(1L, "Samah", "samahmahdi22@gmail.com","0511111111",
                new Address("street1","city1","state1","1111","Country1"),
                new Role(1,"ADMIN", Set.of(new Privilege(1,"READ_WRITE"))) ));
        //when
        List<UserDto> userDtos = userServiceImp.getAllUsers();

        //then
        assertEquals(userDtos.size(),userList.size());
    }


}