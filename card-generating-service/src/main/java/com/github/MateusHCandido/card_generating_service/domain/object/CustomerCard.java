package com.github.MateusHCandido.card_generating_service.domain.object;

import com.github.MateusHCandido.card_generating_service.domain.entities.Card;

import java.math.BigDecimal;

public class CustomerCard {

    private String customerCpf;
    private Card customerCard;
    private BigDecimal cardLimit;


    public CustomerCard() {
    }
    public CustomerCard(String customerCpf, Card customerCard, BigDecimal cardLimit) {
        this.customerCpf = customerCpf;
        this.customerCard = customerCard;
        this.cardLimit = cardLimit;
    }


    public String getCustomerCpf() {
        return customerCpf;
    }

    public void setCustomerCpf(String customerCpf) {
        this.customerCpf = customerCpf;
    }

    public Card getCustomerCard() {
        return customerCard;
    }

    public void setCustomerCard(Card customerCard) {
        this.customerCard = customerCard;
    }

    public BigDecimal getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(BigDecimal cardLimit) {
        this.cardLimit = cardLimit;
    }
}
