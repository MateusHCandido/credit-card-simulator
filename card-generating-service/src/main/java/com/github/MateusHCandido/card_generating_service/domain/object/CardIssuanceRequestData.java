package com.github.MateusHCandido.card_generating_service.domain.object;

import java.math.BigDecimal;

public class CardIssuanceRequestData {

    private String customerCpf;
    private String customerAddress;
    private BigDecimal cardLimitReleased;

    public CardIssuanceRequestData(){}

    public CardIssuanceRequestData(String customerCpf, String customerAddress, BigDecimal cardLimitReleased) {
        this.customerCpf = customerCpf;
        this.customerAddress = customerAddress;
        this.cardLimitReleased = cardLimitReleased;
    }

    public String getCustomerCpf() {
        return customerCpf;
    }

    public void setCustomerCpf(String customerCpf) {
        this.customerCpf = customerCpf;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public BigDecimal getCardLimitReleased() {
        return cardLimitReleased;
    }

    public void setCardLimitReleased(BigDecimal cardLimitReleased) {
        this.cardLimitReleased = cardLimitReleased;
    }
}
