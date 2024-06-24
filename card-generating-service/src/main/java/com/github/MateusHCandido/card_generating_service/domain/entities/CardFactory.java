package com.github.MateusHCandido.card_generating_service.domain.entities;

import com.github.MateusHCandido.card_generating_service.domain.enums.CardBrand;
import com.github.MateusHCandido.card_generating_service.domain.enums.CardStatus;

import java.math.BigDecimal;

public class CardFactory {

    private Card card;


    public Card generateActiveCardWithMastercardFlag(String name, BigDecimal requiredIncome, BigDecimal basicLimit){
        return new Card(name, CardBrand.MASTERCARD, requiredIncome, basicLimit, CardStatus.ACTIVE);
    }

    public Card generateBlockCardWithMastercardFlag(String name, BigDecimal requiredIncome, BigDecimal basicLimit){
        return new Card(name, CardBrand.MASTERCARD, requiredIncome, basicLimit, CardStatus.BLOCKED);
    }

    public Card generateActiveCardWithVisaFlag(String name, BigDecimal requiredIncome, BigDecimal basicLimit){
        return new Card(name, CardBrand.VISA, requiredIncome, basicLimit, CardStatus.ACTIVE);
    }

    public Card generateBlockCardWithVisaFlag(String name, BigDecimal requiredIncome, BigDecimal basicLimit){
        return new Card(name, CardBrand.VISA, requiredIncome, basicLimit, CardStatus.BLOCKED);
    }


}
