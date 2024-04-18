package com.samah.userservice.service;

import com.samah.userservice.dto.UserDto;
import com.samah.userservice.exception.UserNotFoundException;
import com.samah.userservice.entity.User;
import com.samah.userservice.mapper.UserMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.samah.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    private UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserDto getUserByName(String name) {
        Optional<User> user = userRepository.findByName(name);
        if(user.isPresent())
            return userMapper.UserToUserDto(user.get());
        else throw new UserNotFoundException("User not found");
    }

    @Override
    @Cacheable(value = "userById", key = "#id")
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
                return userMapper.UserToUserDto(user.get());
         else throw new UserNotFoundException("User not found");
        //return user.orElse(null);
    }

    @Override
    public UserDto addUser(User user) {
        return userMapper.UserToUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(u ->userMapper.UserToUserDto(u))
                .toList();
    }

    @Override
    public UserDto updateUser(Long id, UserDto dto) {
        //User dbUser = userRepository.findById(id).get(); //
        User user = userMapper.UserDtoToUser(dto);
        user.setId(id);
        if(!userRepository.existsById(user.getId()))
            throw new IllegalArgumentException("User does not exist");
        return userMapper.UserToUserDto(user);
    }
}

