package com.samah.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data  @AllArgsConstructor @NoArgsConstructor
public class User {
    private Long Id;
    private String name;
    private String email;
    private String phone;
    private Address address;
    private String jobTitle;
    private String role;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Set<Role> roles;
}
