package com.github.MateusHCandido.card_generating_service.application.gateway;

import com.github.MateusHCandido.card_generating_service.application.gateway.exception.CardAlreadyActiveException;
import com.github.MateusHCandido.card_generating_service.application.gateway.exception.CardAlreadyBlockedException;
import com.github.MateusHCandido.card_generating_service.application.gateway.exception.CardAlreadyCanceledException;
import com.github.MateusHCandido.card_generating_service.domain.enums.CardBrand;
import com.github.MateusHCandido.card_generating_service.domain.enums.CardStatus;
import com.github.MateusHCandido.card_generating_service.domain.entities.Card;
import com.github.MateusHCandido.card_generating_service.domain.entities.CardFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardUseCaseTest {

    @Mock
    private AppCardRepository repository;

    @Mock
    private CardFactory factory;

    @InjectMocks
    private CardUseCase useCase;


    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Should create blocked cards with the MASTERCARD and VISA brand and then return a card")
    public void createCardCase1(){
        Card blockedMasterCard = new Card("Test 1", CardBrand.MASTERCARD, new BigDecimal(1000), new BigDecimal(1000), CardStatus.BLOCKED);
        Card blockedVisa = new Card("Test 2", CardBrand.VISA, new BigDecimal(1000), new BigDecimal(1000), CardStatus.BLOCKED);


        Mockito.when(factory.generateBlockCardWithMastercardFlag("Test 1", new BigDecimal(1000), new BigDecimal(1000)))
                .thenReturn(blockedMasterCard);
        Mockito.when(repository.saveCard(blockedMasterCard)).thenReturn(blockedMasterCard);

        Mockito.when(factory.generateBlockCardWithMastercardFlag("Test 2", new BigDecimal(1000), new BigDecimal(1000)))
                .thenReturn(blockedVisa);
        Mockito.when(repository.saveCard(blockedVisa)).thenReturn(blockedVisa);

        Card resultBlockedMasterCard = repository.saveCard(blockedMasterCard);
        Card resultBlockedVisa = repository.saveCard(blockedVisa);


        assertNotNull(resultBlockedMasterCard);
        assertTrue(resultBlockedMasterCard.getCardBrand().MASTERCARD.equals(CardBrand.MASTERCARD));
        assertTrue(resultBlockedMasterCard.getCardStatus().equals(CardStatus.BLOCKED));

        assertNotNull(resultBlockedVisa);
        assertTrue(resultBlockedVisa.getCardBrand().VISA.equals(CardBrand.VISA));
        assertTrue(resultBlockedVisa.getCardStatus().equals(CardStatus.BLOCKED));
    }


    @Test
    @DisplayName("Should throw exception when try create card with brandCard parameter invalid")
    public void createCardCase2(){
        String name = "test";
        String reqIncome = "100";
        String basicLimit = "100";
        List<String> invalidBrands = Arrays.asList(
                "VIZA",         // Typo
                "VISAA",        // Extra character
                "VSA",          // Missing character
                "VIS",          // Missing character
                "MASTERCAR",    // Missing character
                "MASTERRCARD",  // Extra character
                "MASTERCAD",    // Typo
                "MASTERCRD",    // Typo
                "MSTERCRD",     // Missing character
                "MSTERCARD",    // Missing character
                "MCARD",        // Truncated
                "CARDMASTER",   // Reversed words
                "VISA MASTER",  // Two valid parts combined incorrectly
                "",             // Empty string
                " ",            // Space
                "MASTERCARD ",  // Trailing space
                " MASTERCARD"   // Leading space
        );


        invalidBrands.forEach(invalidBrand ->
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> useCase.saveCard(name, invalidBrand, reqIncome, basicLimit)));

    }

    @Test
    @DisplayName("Should return a collection of cards where the required income is less than or equal to the submitted parameter")
    public void findByCardReqIncomeLessThanEqualCase1(){
        Long reqIncome = 3000l;

        List<Card> cardListWithReqIncomeBellowOrEqualOf3000 = Arrays.asList(
                new Card("Test 1", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new Card("Test 2", CardBrand.MASTERCARD, new BigDecimal("1500"), new BigDecimal("1000"), CardStatus.ACTIVE),
                new Card("Test 3", CardBrand.VISA, new BigDecimal("2000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new Card("Test 4", CardBrand.VISA, new BigDecimal("2500"), new BigDecimal("1000"), CardStatus.ACTIVE),
                new Card("Test 5", CardBrand.MASTERCARD, new BigDecimal("3000"), new BigDecimal("2000"), CardStatus.BLOCKED));

        Mockito.when(repository.findByCardReqIncomeLessThanEqual(new BigDecimal( String.valueOf(reqIncome) ))).thenReturn(cardListWithReqIncomeBellowOrEqualOf3000);

        List<Card> result = repository.findByCardReqIncomeLessThanEqual(new BigDecimal(  String.valueOf(reqIncome) ));

        assertTrue(!result.isEmpty());
        assertTrue(result.size() == 5);
        assertTrue(result.get(0).getCardBrand().equals(CardBrand.MASTERCARD));
        assertTrue(result.get(4).getCardBrand().equals(CardBrand.MASTERCARD));
    }

    @Test
    @DisplayName("Should return a collection of cards empty")
    public void findByCardReqIncomeLessThanEqualCase2(){
        Long reqIncome = 3000l;

        List<Card> cardListWithReqIncomeBellowOrEqualOf3000 = new ArrayList<>();

        Mockito.when(repository.findByCardReqIncomeLessThanEqual(new BigDecimal( String.valueOf(reqIncome) ))).thenReturn(cardListWithReqIncomeBellowOrEqualOf3000);

        List<Card> result = repository.findByCardReqIncomeLessThanEqual(new BigDecimal(  String.valueOf(reqIncome) ));

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return a collection of cards where the required income is less than or equal to the submitted parameter")
    public void findByCardReqIncomeGreaterThanEqualCase1(){
        Long reqIncome = 1000l;

        List<Card> cardListWithReqIncomeGreaterOrEqualOf3000 = Arrays.asList(
                new Card("Test 1", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new Card("Test 2", CardBrand.MASTERCARD, new BigDecimal("1500"), new BigDecimal("1000"), CardStatus.ACTIVE),
                new Card("Test 3", CardBrand.VISA, new BigDecimal("2000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new Card("Test 4", CardBrand.VISA, new BigDecimal("2500"), new BigDecimal("1000"), CardStatus.ACTIVE),
                new Card("Test 5", CardBrand.MASTERCARD, new BigDecimal("3000"), new BigDecimal("2000"), CardStatus.BLOCKED));

        Mockito.when(repository.findByCardReqIncomeGreaterThanEqual(new BigDecimal( String.valueOf(reqIncome) ))).thenReturn(cardListWithReqIncomeGreaterOrEqualOf3000);

        List<Card> result = repository.findByCardReqIncomeGreaterThanEqual(new BigDecimal(  String.valueOf(reqIncome) ));

        assertTrue(!result.isEmpty());
        assertTrue(result.size() == 5);
        assertTrue(result.get(0).getCardBrand().equals(CardBrand.MASTERCARD));
        assertTrue(result.get(4).getCardBrand().equals(CardBrand.MASTERCARD));
    }

    @Test
    @DisplayName("Should return a collection of cards empty")
    public void findByCardReqIncomeGreaterThanEqualCase2(){
        Long reqIncome = 6000l;

        List<Card> cardListWithReqIncomeBellowOrEqualOf3000 = new ArrayList<>();

        Mockito.when(repository.findByCardReqIncomeGreaterThanEqual(new BigDecimal( String.valueOf(reqIncome) ))).thenReturn(cardListWithReqIncomeBellowOrEqualOf3000);

        List<Card> result = repository.findByCardReqIncomeGreaterThanEqual(new BigDecimal(  String.valueOf(reqIncome) ));

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return a list with blocked cards")
    public void listBlockedCardCase1(){
        List<Card> blockedCards = Arrays.asList(
                new Card("Test 1", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new Card("Test 2", CardBrand.MASTERCARD, new BigDecimal("1500"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new Card("Test 3", CardBrand.VISA, new BigDecimal("2000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new Card("Test 4", CardBrand.VISA, new BigDecimal("2500"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new Card("Test 5", CardBrand.MASTERCARD, new BigDecimal("3000"), new BigDecimal("2000"), CardStatus.BLOCKED));

        Mockito.when(repository.listBlockedCards()).thenReturn(blockedCards);

        List<Card> result = repository.listBlockedCards();

        assertTrue(!result.isEmpty());
        assertTrue(result.size() == 5);

        result.forEach(card -> assertTrue(card.getCardStatus().equals(CardStatus.BLOCKED)));
    }

    @Test
    @DisplayName("Should return a list with active cards")
    public void listActiveCardCase1(){
        List<Card> blockedCards = Arrays.asList(
                new Card("Test 1", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.ACTIVE),
                new Card("Test 2", CardBrand.MASTERCARD, new BigDecimal("1500"), new BigDecimal("1000"), CardStatus.ACTIVE),
                new Card("Test 3", CardBrand.VISA, new BigDecimal("2000"), new BigDecimal("1000"), CardStatus.ACTIVE),
                new Card("Test 4", CardBrand.VISA, new BigDecimal("2500"), new BigDecimal("1000"), CardStatus.ACTIVE),
                new Card("Test 5", CardBrand.MASTERCARD, new BigDecimal("3000"), new BigDecimal("2000"), CardStatus.ACTIVE));

        Mockito.when(repository.listActiveCards()).thenReturn(blockedCards);

        List<Card> result = repository.listActiveCards();

        assertTrue(!result.isEmpty());
        assertTrue(result.size() == 5);

        result.forEach(card -> assertTrue(card.getCardStatus().equals(CardStatus.ACTIVE)));
    }

    @Test
    @DisplayName("Should return a list with canceled cards")
    public void listCanceledCardCase1(){
        List<Card> blockedCards = Arrays.asList(
                new Card("Test 1", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.CANCELED),
                new Card("Test 2", CardBrand.MASTERCARD, new BigDecimal("1500"), new BigDecimal("1000"), CardStatus.CANCELED),
                new Card("Test 3", CardBrand.VISA, new BigDecimal("2000"), new BigDecimal("1000"), CardStatus.CANCELED),
                new Card("Test 4", CardBrand.VISA, new BigDecimal("2500"), new BigDecimal("1000"), CardStatus.CANCELED),
                new Card("Test 5", CardBrand.MASTERCARD, new BigDecimal("3000"), new BigDecimal("2000"), CardStatus.CANCELED));

        Mockito.when(repository.listCanceledCards()).thenReturn(blockedCards);

        List<Card> result = repository.listCanceledCards();

        assertTrue(!result.isEmpty());
        assertTrue(result.size() == 5);

        result.forEach(card -> assertTrue(card.getCardStatus().equals(CardStatus.CANCELED)));
    }

    @Test
    @DisplayName("Should block a card")
    public void blockCardCase1() throws CardAlreadyBlockedException {
        Card canceledCard = new Card("Test 1", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.CANCELED);
        Card activeCard = new Card("Test 2", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.ACTIVE);

        when(repository.findCardByCardName("Test 1")).thenReturn(canceledCard);
        when(repository.findCardByCardName("Test 2")).thenReturn(activeCard);
        useCase.blockCard("Test 1");
        useCase.blockCard("Test 2");

        assertEquals(CardStatus.BLOCKED, canceledCard.getCardStatus());
        assertEquals(CardStatus.BLOCKED, activeCard.getCardStatus());

        verify(repository, times(1)).blockCard(canceledCard);
        verify(repository, times(1)).blockCard(activeCard);
    }

    @Test
    @DisplayName("Should throw CardAlreadyBlockedException")
    public void blockCardCase2(){
        Card card = new Card("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED);

        when(repository.findCardByCardName("Test")).thenReturn(card);

        assertThrows(CardAlreadyBlockedException.class, () -> {
            useCase.blockCard("Test");
        });

        verify(repository, never()).blockCard(any(Card.class));
    }

    @Test
    @DisplayName("Should active a card")
    public void activeCardCase1() throws CardAlreadyActiveException {
        Card canceledCard = new Card("Test 1", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.CANCELED);
        Card blockedCard = new Card("Test 2", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED);

        when(repository.findCardByCardName("Test 1")).thenReturn(canceledCard);
        when(repository.findCardByCardName("Test 2")).thenReturn(blockedCard);

        useCase.activateCard("Test 1");
        useCase.activateCard("Test 2");

        assertEquals(CardStatus.ACTIVE, canceledCard.getCardStatus());
        assertEquals(CardStatus.ACTIVE, blockedCard.getCardStatus());

        verify(repository, times(1)).activateCard(canceledCard);
        verify(repository, times(1)).activateCard(blockedCard);
    }

    @Test
    @DisplayName("Should throw CardAlreadyActiveException")
    public void activeCardCase2(){
        Card card = new Card("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.ACTIVE);

        when(repository.findCardByCardName("Test")).thenReturn(card);

        assertThrows(CardAlreadyActiveException.class, () -> {
            useCase.activateCard("Test");
        });

        verify(repository, never()).activateCard(any(Card.class));
    }

    @Test
    @DisplayName("Should cancel a card")
    public void cancelCardCase1() throws CardAlreadyCanceledException {
        Card activeCard = new Card("Test 1", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.ACTIVE);
        Card blockedCard = new Card("Test 2", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED);

        when(repository.findCardByCardName("Test 1")).thenReturn(activeCard);
        when(repository.findCardByCardName("Test 2")).thenReturn(blockedCard);

        useCase.cancelCard("Test 1");
        useCase.cancelCard("Test 2");

        assertEquals(CardStatus.CANCELED, activeCard.getCardStatus());
        assertEquals(CardStatus.CANCELED, blockedCard.getCardStatus());

        verify(repository, times(1)).cancelCard(activeCard);
        verify(repository, times(1)).cancelCard(blockedCard);
    }

    @Test
    @DisplayName("Should throw CardAlreadyCanceledException")
    public void cancelCardCase2(){
        Card card = new Card("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.CANCELED);

        when(repository.findCardByCardName("Test")).thenReturn(card);

        assertThrows(CardAlreadyCanceledException.class, () -> {
            useCase.cancelCard("Test");
        });

        verify(repository, never()).cancelCard(any(Card.class));
    }
}