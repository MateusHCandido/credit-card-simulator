package com.github.MateusHCandido.validation_credit_service.infra.gateway;

import com.github.MateusHCandido.validation_credit_service.application.model.ApprovedCard;
import com.github.MateusHCandido.validation_credit_service.application.model.ReturnCreditAssessment;
import com.github.MateusHCandido.validation_credit_service.infra.clients.card.GetCardDto;
import com.github.MateusHCandido.validation_credit_service.infra.clients.customer.CustomerCard;
import com.github.MateusHCandido.validation_credit_service.application.model.CustomerSituation;
import com.github.MateusHCandido.validation_credit_service.infra.clients.card.CardResourceClient;
import com.github.MateusHCandido.validation_credit_service.infra.controller.dto.CustomerDto;
import com.github.MateusHCandido.validation_credit_service.infra.controller.dto.CustomerGetDto;
import com.github.MateusHCandido.validation_credit_service.infra.clients.customer.CustomerResourceClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditValidatorService {

    @Autowired
    private CustomerResourceClient customerResourceClient;

    @Autowired
    private CardResourceClient cardResourceClient;

    public CustomerSituation checkCustomerSituation(String customerCpf) {
        try{
            ResponseEntity<CustomerDto> customerDto = customerResourceClient.findCustomerByCpf( new CustomerGetDto(customerCpf) );
            ResponseEntity<List<CustomerCard>> customerCard = cardResourceClient.listCardByCustomer( new CustomerGetDto(customerCpf) );

            CustomerSituation situation = new CustomerSituation(customerDto.getBody(), customerCard.getBody());
            return situation;
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


}
