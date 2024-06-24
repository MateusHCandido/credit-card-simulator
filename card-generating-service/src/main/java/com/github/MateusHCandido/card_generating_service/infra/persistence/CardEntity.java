package com.github.MateusHCandido.card_generating_service.infra.persistence;

import com.github.MateusHCandido.card_generating_service.domain.enums.CardBrand;
import com.github.MateusHCandido.card_generating_service.domain.enums.CardStatus;
import com.github.MateusHCandido.card_generating_service.domain.entities.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    @Column(unique = true)
    private String cardName;
    @Enumerated(EnumType.STRING)
    private CardBrand cardBrand;
    private BigDecimal cardReqIncome;
    private BigDecimal cardBasicLimit;
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    public CardEntity(Card card){
        this.cardName = card.getCardName();
        this.cardBrand = card.getCardBrand();
        this.cardReqIncome = card.getCardReqIncome();
        this.cardBasicLimit = card.getCardBasicLimit();
        this.cardStatus = card.getCardStatus();
    }

    public CardEntity(String cardName, CardBrand cardBrand, BigDecimal cardReqIncome, BigDecimal cardBasicLimit, CardStatus cardStatus) {
        this.cardName = cardName;
        this.cardBrand = cardBrand;
        this.cardReqIncome = cardReqIncome;
        this.cardBasicLimit = cardBasicLimit;
        this.cardStatus = cardStatus;
    }


}
