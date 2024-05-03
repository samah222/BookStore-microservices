package com.samah.mailservice.consumer;

import com.samah.mailservice.payload.Mail;
import com.samah.mailservice.service.MailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqMailConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqMailConsumer.class);
    @Autowired
    private MailSenderService service;

    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
    public void consume(Mail mail){
        LOGGER.info(String.format(" RabbitmqMailConsumer - employee received : %s",mail));
        service.sendNewMail(mail.getSendTo(),mail.getSubject(), mail.getBody());
    }
}
