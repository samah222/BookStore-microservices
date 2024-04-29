package com.samah.userservice.event;

import com.samah.userservice.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter

public class RegistrationCompleteEvent extends ApplicationEvent {
    private UserDto dto;
    private String ApplicationUrl;

    public RegistrationCompleteEvent(UserDto dto, String Url) {
        super(dto);
        this.ApplicationUrl = Url;
        this.dto = dto;
    }
}
