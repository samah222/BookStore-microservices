package com.samah.userservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.topic-exchange.name}")
    private String topicExchangeName;

    @Value("${rabbitmq.routing-key.name}")
    private String routingKey;

    @Value("${rabbitmq.routingMail-key.name}")
    private String routingJsonKey;

    @Value("${rabbitmq.json.queue.name}")
    private String jsonQueueName;

    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(topicExchange())
                .with(routingKey);
    }

    @Bean
    public Queue jsonqueue(){
        return new Queue(jsonQueueName);
    }

    @Bean
    public Binding jsonbinding(){
        return BindingBuilder.bind(jsonqueue())
                .to(topicExchange())
                .with(routingJsonKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory ){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
