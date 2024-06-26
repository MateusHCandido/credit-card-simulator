package com.github.MateusHCandido.validation_credit_service.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.MateusHCandido.validation_credit_service.application.model.RequestDataCardIssuance;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestCardIssuancePublisher {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queueCardIssuance;

    @Autowired
    private ObjectMapper mapper;


    public void requestCard(RequestDataCardIssuance request) throws JsonProcessingException {
        String message = convertIntoJson(request);
        System.out.println("Preparing to send message: " + message);
        rabbitTemplate.convertAndSend(queueCardIssuance.getName(), message);
        System.out.println("Sent message: " + message);
    }

    private String convertIntoJson(RequestDataCardIssuance request) throws JsonProcessingException {
        return mapper.writeValueAsString(request);
    }
}
