package com.github.MateusHCandido.card_generating_service.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.MateusHCandido.card_generating_service.domain.object.RequestDataCardIssuance;
import com.github.MateusHCandido.card_generating_service.infra.persistence.CardEntity;
import com.github.MateusHCandido.card_generating_service.infra.persistence.CardRepository;
import com.github.MateusHCandido.card_generating_service.infra.persistence.CustomerCardEntity;
import com.github.MateusHCandido.card_generating_service.infra.persistence.CustomerCardRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CardIssuanceSubscriber {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CustomerCardRepository customerCardRepository;

    @Autowired
    private ObjectMapper mapper;

    @RabbitListener(queues = "${mq.queues.credit-assessment}")
    public void receiveIssuanceRequest(@Payload String payload) {
        System.out.println("Received raw message: " + payload);
        try {
            RequestDataCardIssuance response = mapper.readValue(payload, RequestDataCardIssuance.class);
            System.out.println("Deserialized message: " + response.toString());

            CardEntity cardEntity = cardRepository.findByCardName(response.getCardName());
            System.out.println("CardEntity found: " + cardEntity);

            CustomerCardEntity customerCardEntity = new CustomerCardEntity();
            customerCardEntity.setCard(cardEntity);
            customerCardEntity.setCustomerCpf(response.getCustomerCpf());
            customerCardEntity.setCustomerCardLimit(response.getCardLimitApproved());

            customerCardRepository.save(customerCardEntity);
            System.out.println("CustomerCardEntity saved: " + customerCardEntity);

        } catch (JsonProcessingException e) {
            System.err.println("Error processing JSON: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}