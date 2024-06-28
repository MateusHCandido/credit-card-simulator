package com.github.MateusHCandido.validation_credit_service.infra.clients.customer;

import com.github.MateusHCandido.validation_credit_service.infra.clients.card.Card;


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
