package com.samah.mailservice.consumer;

import com.samah.mailservice.payload.Mail;
import com.samah.mailservice.service.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
public class MailConsumer {
    @Autowired
    private MailSenderService service;
    @KafkaListener(topics = "user_topic5", groupId = "myGroup")
    public void consumeMsg(Mail mail){
        service.sendNewMail(mail.getSendTo(),mail.getSubject(),mail.getBody());
        log.info(format("consuming the mail %s", mail.toString()));
    }
}
