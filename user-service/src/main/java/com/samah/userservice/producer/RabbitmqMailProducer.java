package com.samah.userservice.producer;

import com.samah.userservice.payload.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqMailProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqMailProducer.class);

    @Value("${rabbitmq.routingMail-key.name}")
    private String routingJsonKey;

    @Value("${rabbitmq.topic-exchange.name}")
    private String exchange;

    public RabbitmqMailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Mail mail){
        LOGGER.info(String.format("RabbitmqMailProducer - email published %s",mail.toString()));
        rabbitTemplate.convertAndSend(exchange,routingJsonKey,mail);
    }


}
