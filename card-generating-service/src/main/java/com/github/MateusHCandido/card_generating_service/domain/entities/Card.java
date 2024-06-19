package com.github.MateusHCandido.card_generating_service.domain.entities;

import com.github.MateusHCandido.card_generating_service.domain.CardBrand;
import com.github.MateusHCandido.card_generating_service.domain.CardStatus;

import java.math.BigDecimal;

public class Card {
    private String cardName;
    private CardBrand cardBrand;
    private BigDecimal cardReqIncome;
    private BigDecimal cardBasicLimit;
    private CardStatus cardStatus;

    public Card(){}
    public Card(String cardName, CardBrand cardBrand, BigDecimal cardReqIncome, BigDecimal cardBasicLimit, CardStatus cardStatus) {
        this.cardName = cardName;
        this.cardBrand = cardBrand;
        this.cardReqIncome = cardReqIncome;
        this.cardBasicLimit = cardBasicLimit;
        this.cardStatus = cardStatus;
    }


    public void cancelCard(Card card){
        card.setCardStatus(CardStatus.CANCELED);
    }

    public void activateCard(Card card){
        card.setCardStatus(CardStatus.ACTIVE);
    }

    public void blockCard(Card card){
        card.setCardStatus(CardStatus.BLOCKED);
    }

    public CardBrand getCardBrand() {
        return cardBrand;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCardName() {
        return cardName;
    }

    public BigDecimal getCardReqIncome() {
        return cardReqIncome;
    }

    public BigDecimal getCardBasicLimit() {
        return cardBasicLimit;
    }
}
