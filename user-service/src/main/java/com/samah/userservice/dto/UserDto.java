package com.samah.userservice.dto;

import com.samah.userservice.entity.Address;
import com.samah.userservice.entity.Role;

 public record UserDto(Long id, String name, String email, String phone, Address address, Role role) {

}

 
