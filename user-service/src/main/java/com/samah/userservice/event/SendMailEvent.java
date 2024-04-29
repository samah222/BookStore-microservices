package com.samah.userservice.event;

import com.samah.userservice.dto.UserDto;
import com.samah.userservice.entity.VerificationToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SendMailEvent extends ApplicationEvent {
    private UserDto userDto;

    public SendMailEvent(UserDto userDto) {
        super(userDto);
        this.userDto = userDto;
    }
}
