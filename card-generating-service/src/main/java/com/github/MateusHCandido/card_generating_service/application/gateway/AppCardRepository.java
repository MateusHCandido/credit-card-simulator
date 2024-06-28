package com.github.MateusHCandido.card_generating_service.application.gateway;

import com.github.MateusHCandido.card_generating_service.domain.entities.Card;

import java.math.BigDecimal;
import java.util.List;

public interface AppCardRepository {

    Card saveCard(Card card);

    List<Card> findByCardReqIncomeLessThanEqual(BigDecimal cardReqIncome);

    List<Card> findByCardReqIncomeGreaterThanEqual(BigDecimal cardReqIncome);

    List<Card> listBlockedCards();

    List<Card> listActiveCards();

    List<Card> listCanceledCards();

    void blockCard(Card card);

    void cancelCard(Card card);

    void activateCard(Card card);

    Card findCardByCardName(String cardName);
}
