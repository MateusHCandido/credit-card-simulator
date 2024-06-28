package com.github.MateusHCandido.card_generating_service.infra.controller.dto;

import com.github.MateusHCandido.card_generating_service.domain.entities.Card;
import com.github.MateusHCandido.card_generating_service.domain.object.CustomerCard;
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
