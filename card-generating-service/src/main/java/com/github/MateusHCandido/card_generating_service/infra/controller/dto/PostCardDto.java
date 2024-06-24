package com.github.MateusHCandido.card_generating_service.infra.controller.dto;

public class PostCardDto {
    private String cardName;
    private String cardBrand;
    private String cardReqIncome;
    private String cardBasicLimit;

    public PostCardDto(String cardName, String cardBrand, String cardReqIncome, String cardBasicLimit) {
        this.cardName = cardName;
        this.cardBrand = cardBrand;
        this.cardReqIncome = cardReqIncome;
        this.cardBasicLimit = cardBasicLimit;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public String getCardReqIncome() {
        return cardReqIncome;
    }

    public String getCardBasicLimit() {
        return cardBasicLimit;
    }
}
