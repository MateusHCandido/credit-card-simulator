package com.github.MateusHCandido.validation_credit_service.infra.gateway;

import com.github.MateusHCandido.validation_credit_service.application.model.*;
import com.github.MateusHCandido.validation_credit_service.infra.clients.card.CustomerCardDto;
import com.github.MateusHCandido.validation_credit_service.infra.clients.card.GetCardDto;
import com.github.MateusHCandido.validation_credit_service.infra.clients.customer.CustomerCard;
import com.github.MateusHCandido.validation_credit_service.infra.clients.card.CardResourceClient;
import com.github.MateusHCandido.validation_credit_service.infra.controller.dto.CustomerDto;
import com.github.MateusHCandido.validation_credit_service.infra.controller.dto.CustomerGetDto;
import com.github.MateusHCandido.validation_credit_service.infra.clients.customer.CustomerResourceClient;
import com.github.MateusHCandido.validation_credit_service.infra.gateway.exception.CardRequestException;
import com.github.MateusHCandido.validation_credit_service.infra.mqueue.RequestCardIssuancePublisher;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreditValidatorService {

    @Autowired
    private CustomerResourceClient customerResourceClient;

    @Autowired
    private CardResourceClient cardResourceClient;

    @Autowired
    private RequestCardIssuancePublisher publisher;

    public CustomerSituation checkCustomerSituation(String customerCpf) {
        try{
            ResponseEntity<CustomerDto> customerDto = customerResourceClient.findCustomerByCpf( new CustomerGetDto(customerCpf) );
            ResponseEntity<List<CustomerCardDto>> customerCard = cardResourceClient.listCardByCustomer( new CustomerGetDto(customerCpf) );

            return CustomerSituation
                    .builder()
                    .customer(customerDto.getBody())
                    .cards(customerCard.getBody())
                    .build();
        }catch (FeignException.FeignClientException feignClientException){
            if (feignClientException.status() == HttpStatus.NOT_FOUND.value()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
            }
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failure on connection between services");
        }
    }

    public ReturnCreditAssessment evaluateCredit(String customerCpf, Long customerIncome){
        try{
            ResponseEntity<CustomerDto> customerDto = customerResourceClient.findCustomerByCpf( new CustomerGetDto(customerCpf) );
            ResponseEntity<List<GetCardDto>> cardsAvailable = cardResourceClient.findByCardReqIncomeLessThanEqual(customerIncome);

            List<ApprovedCard> approvedCards = getApprovedCards(customerDto, cardsAvailable);

            return new ReturnCreditAssessment(approvedCards);

        }catch (FeignException.FeignClientException feignClientException){
            if (feignClientException.status() == HttpStatus.NOT_FOUND.value()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
            }
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failure on connection between services");
        }
    }

    private static List<ApprovedCard> getApprovedCards(ResponseEntity<CustomerDto> customerDto, ResponseEntity<List<GetCardDto>> cardsAvailable) {
        List<GetCardDto> cards = cardsAvailable.getBody();
        List<ApprovedCard> approvedCards = cards.stream().map(card -> {

            CustomerDto customer = customerDto.getBody();

            BigDecimal cardBasicLimit = new BigDecimal(card.getCardBasicLimit());
            BigDecimal customerAge = BigDecimal.valueOf( LocalDate.now().getYear() - customer.getCustomerBirthdate().getYear() );
            BigDecimal customerAgeDivide = customerAge.divide(BigDecimal.valueOf(10));
            BigDecimal approvedLimit = customerAgeDivide.multiply(cardBasicLimit);

            return new ApprovedCard(card.getCardName(), card.getCardBrand(), approvedLimit);
        }).collect(Collectors.toList());
        return approvedCards;
    }

    public RequestCardIssuanceProtocol requestCardIssuance(RequestDataCardIssuance requestDataCardIssuance){
        try{
            publisher.requestCard(requestDataCardIssuance);
            return new RequestCardIssuanceProtocol(UUID.randomUUID().toString());

        }catch (Exception e){
            throw new CardRequestException(e.getMessage());
        }
    }
}
