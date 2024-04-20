package com.samah.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
//@Transactional
public class Employee extends User{
    @Column(nullable = false, length = 50)
    private String jobTitle;
    private BigDecimal salary;
    private String employeeDetails;
    private String department;
    private String position;
    @Past
    private LocalDate dateOfBirth;
}
