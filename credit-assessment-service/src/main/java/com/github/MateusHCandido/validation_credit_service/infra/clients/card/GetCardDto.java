package com.github.MateusHCandido.validation_credit_service.infra.clients.card;

import com.github.MateusHCandido.validation_credit_service.application.enums.CardBrand;
import com.github.MateusHCandido.validation_credit_service.application.enums.CardStatus;

import java.math.BigDecimal;

public class GetCardDto {
    private String cardName;
    private String cardBrand;
    private String cardReqIncome;
    private String cardBasicLimit;
    private String cardStatus;

    public GetCardDto(){}

    public GetCardDto(String cardName, CardBrand cardBrand, BigDecimal cardReqIncome, BigDecimal cardBasicLimit, CardStatus cardStatus) {
        this.cardName = cardName;
        this.cardBrand = String.valueOf( cardBrand );
        this.cardReqIncome = String.valueOf( cardReqIncome );
        this.cardBasicLimit = String.valueOf( cardBasicLimit );
        this.cardStatus = String.valueOf( cardStatus );
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardReqIncome() {
        return cardReqIncome;
    }

    public String getCardBasicLimit() {
        return cardBasicLimit;
    }

    public String getCardStatus() {
        return cardStatus;
    }
}
