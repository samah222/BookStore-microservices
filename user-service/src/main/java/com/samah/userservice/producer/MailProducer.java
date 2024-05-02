package com.samah.userservice.producer;

import com.samah.userservice.payload.Mail;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MailProducer {
    public MailProducer(KafkaTemplate<String, Mail> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    private KafkaTemplate<String, Mail> kafkaTemplate;

    public void sendMessage(Mail mail){
        Message<Mail> message = MessageBuilder.withPayload(mail).
                setHeader(KafkaHeaders.TOPIC, "user_topic5").build();
        kafkaTemplate.send(message);
    }
}
