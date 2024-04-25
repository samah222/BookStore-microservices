package com.samah.userservice.dto;

import com.samah.userservice.entity.Address;
import com.samah.userservice.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

 public class UserDto {
  private Long id;
  private String name;
  private String email;
  private String phone;
  private Address address;
  private Role role;

}

 
