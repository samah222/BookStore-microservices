package com.samah.userservice.dto;

import com.samah.userservice.entity.Address;
import com.samah.userservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private String name;
    private String email;
    private String password;
    private String matchingPassword;
    private String phone;
    private Address address;
    private Role role;
}
