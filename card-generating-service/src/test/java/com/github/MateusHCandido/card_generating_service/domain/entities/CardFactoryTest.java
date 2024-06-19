package com.github.MateusHCandido.card_generating_service.domain.entities;

import com.github.MateusHCandido.card_generating_service.domain.CardBrand;
import com.github.MateusHCandido.card_generating_service.domain.CardStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CardFactoryTest {

    @Test
    @DisplayName("Should create an active card with the mastercard brand")
    void generateCardWithCardFactoryCaseUse1() {
        CardFactory factory = new CardFactory();
        Card card = factory.generateActiveCardWithMastercardFlag("card test", new BigDecimal("10000"),  new BigDecimal("10000"));

        assertNotNull(card);
        assertTrue(CardBrand.MASTERCARD.equals(card.getCardBrand()));
        assertTrue(CardStatus.ACTIVE.equals(card.getCardStatus()));
    }

    @Test
    @DisplayName("Should create an blocked card with the mastercard brand")
    void generateCardWithCardFactoryCaseUse2() {
        CardFactory factory = new CardFactory();
        Card card = factory.generateBlockCardWithMastercardFlag("card test", new BigDecimal("10000"),  new BigDecimal("10000"));

        assertNotNull(card);
        assertTrue(CardBrand.MASTERCARD.equals(card.getCardBrand()));
        assertTrue(CardStatus.BLOCKED.equals(card.getCardStatus()));
    }

    @Test
    @DisplayName("Should create an active card with the VISA brand")
    void generateCardWithCardFactoryCaseUse3() {
        CardFactory factory = new CardFactory();
        Card card = factory.generateActiveCardWithVisaFlag("card test", new BigDecimal("10000"),  new BigDecimal("10000"));

        assertNotNull(card);
        assertTrue(CardBrand.VISA.equals(card.getCardBrand()));
        assertTrue(CardStatus.ACTIVE.equals(card.getCardStatus()));
    }

    @Test
    @DisplayName("Should create and blocked card with the VISA brand")
    void generateCardWithCardFactoryCaseUse4() {
        CardFactory factory = new CardFactory();
        Card card = factory.generateBlockCardWithVisaFlag("card test", new BigDecimal("10000"),  new BigDecimal("10000"));

        assertNotNull(card);
        assertTrue(CardBrand.VISA.equals(card.getCardBrand()));
        assertTrue(CardStatus.BLOCKED.equals(card.getCardStatus()));
    }
}