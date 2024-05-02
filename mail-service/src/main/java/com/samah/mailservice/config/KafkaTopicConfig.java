package com.samah.mailservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic userTopic(){
        return TopicBuilder.name("user_topic5")
                .partitions(10)
                .replicas(1)
                .build();
    }

}
