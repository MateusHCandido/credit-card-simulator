package com.github.MateusHCandido.validation_credit_service.application.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.credit-assessment}")
    private String cardIssuance;

    @Bean
    public Queue queueCardIssuance(){
        return new Queue(cardIssuance, true);
    }
}
