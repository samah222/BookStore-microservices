package com.samah.mailservice.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Mail {
    private String sendTo;
    private String subject;
    private String body;
}
