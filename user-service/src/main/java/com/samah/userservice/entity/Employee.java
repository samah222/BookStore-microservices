package com.samah.userservice.entity;

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
    private BigDecimal salary;
    private String employeeDetails;
    private String department;
    private String position;
    @Past
    private LocalDate dateOfBirth;
}
