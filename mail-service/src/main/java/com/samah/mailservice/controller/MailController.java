package com.samah.mailservice.controller;

import com.samah.mailservice.consumer.MailConsumer;
import com.samah.mailservice.payload.Mail;
import com.samah.mailservice.producer.MailProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailProducer producer;
    @PostMapping
    public ResponseEntity<String> sendMail(@RequestBody Mail mail){
        producer.sendMessage(mail);
        return ResponseEntity.ok("Success");
    }
}
