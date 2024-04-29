package com.samah.userservice.event.listener;

import com.samah.userservice.dto.UserDto;
import com.samah.userservice.event.RegistrationCompleteEvent;
import com.samah.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //create verification token with link
        UserDto dto = event.getDto();
        String token = UUID.randomUUID().toString();
        //userService.saveVerificationTokenForUSer(dto, token);
        // send email
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
        log.info("the verification url : {}", url);
    }
}
