package com.github.MateusHCandido.validation_credit_service.infra.clients.card;

import com.github.MateusHCandido.validation_credit_service.infra.clients.customer.CustomerCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCardDto {

    private String customerCpf;
    private Card customerCard;
    private BigDecimal cardLimit;

    public CustomerCardDto(CustomerCard domain){
        this.customerCpf = domain.getCustomerCpf();
        this.customerCard = domain.getCustomerCard();
        this.cardLimit = domain.getCardLimit();
    }
}
