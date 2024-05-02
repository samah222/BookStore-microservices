package com.samah.userservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Mail {
    private String sendTo;
    private String subject;
    private String body;
}
