package com.samah.userservice.event.listener;

import com.samah.userservice.event.SendMailEvent;
import com.samah.userservice.mail.MailSenderService;
import com.samah.userservice.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;


public class MailSenderServiceListener implements ApplicationListener<SendMailEvent> {

    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Override
    public void onApplicationEvent(SendMailEvent event) {
        String email = event.getUserDto().getEmail();
        System.out.println("*********** mail event " + verificationTokenRepository.findByUserId(event.getUserDto().getId()).toString());
        mailSenderService.sendNewMail(email, "test verification code mail"
                , "test test"); //verificationTokenRepository.findByUserId(event.getUserDto().getId()).toString()
    }
}
