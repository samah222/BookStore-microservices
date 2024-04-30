package com.samah.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String matchingPassword;

}
