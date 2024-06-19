package com.github.MateusHCandido.card_generating_service.infra.gateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.github.MateusHCandido.card_generating_service.domain.CardBrand;
import com.github.MateusHCandido.card_generating_service.domain.CardStatus;
import com.github.MateusHCandido.card_generating_service.domain.entities.Card;
import com.github.MateusHCandido.card_generating_service.infra.persistence.CardEntity;
import com.github.MateusHCandido.card_generating_service.infra.persistence.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class CardRepositoryJpaTest {

    @Mock
    private CardRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CardRepositoryJpa service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    public CardEntity createCardEntity(Card card){
        return new CardEntity(card);
    }

    @DisplayName("Should create a card with success")
    @Test
    void saveCardCase01(){
        Card domain = new Card("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED);
        CardEntity entity = createCardEntity(domain);

        when(modelMapper.map(domain, CardEntity.class)).thenReturn(entity);
        when(service.saveCard(domain)).thenReturn(domain);

        Card result = service.saveCard(domain);

        assertNotNull(result);
        verify(repository, times(2)).save(entity);
    }


    @DisplayName("Should return a list of cards with required income less than equal sent parameter")
    @Test
    void findByCardReqIncomeLessThanEqualCase01(){
        List<CardEntity> cardEntities = Arrays.asList(
                new CardEntity("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new CardEntity("Test 2", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new CardEntity("Test 3", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new CardEntity("Test 4", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new CardEntity("Test 5", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED)
        );

        List<Card> cardDomains = Arrays.asList(
                new Card("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new Card("Test 2", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new Card("Test 3", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new Card("Test 4", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new Card("Test 5", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED)
        );

        when(modelMapper.map(any(CardEntity.class), eq(Card.class)))
                .thenAnswer( invocationOnMock -> {
                            CardEntity entity = invocationOnMock.getArgument(0);
                            return new Card(entity.getCardName(), entity.getCardBrand(), entity.getCardReqIncome()
                                    , entity.getCardBasicLimit(), entity.getCardStatus());
                        }

                );
        when(repository.findByCardReqIncomeLessThanEqual(new BigDecimal(1000L))).thenReturn(cardEntities);


        List<Card> result = service.findByCardReqIncomeLessThanEqual(new BigDecimal(1000L));

        assertEquals(5, result.size());
        assertTrue(!result.isEmpty());
        verify(repository, times(1)).findByCardReqIncomeLessThanEqual(new BigDecimal(1000L));
    }

    @DisplayName("Should return a list of cards with required income greater than equal sent parameter")
    @Test
    void findByCardReqIncomeGreaterThanEqualCase01(){
        List<CardEntity> cardEntities = Arrays.asList(
                new CardEntity("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new CardEntity("Test 2", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new CardEntity("Test 3", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new CardEntity("Test 4", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new CardEntity("Test 5", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED)
        );


        when(modelMapper.map(any(CardEntity.class), eq(Card.class)))
                .thenAnswer( invocationOnMock -> {
                            CardEntity entity = invocationOnMock.getArgument(0);
                            return new Card(entity.getCardName(), entity.getCardBrand(), entity.getCardReqIncome()
                                    , entity.getCardBasicLimit(), entity.getCardStatus());
                        }

                );
        when(repository.findByCardReqIncomeGreaterThanEqual(new BigDecimal(1000L))).thenReturn(cardEntities);


        List<Card> result = service.findByCardReqIncomeGreaterThanEqual(new BigDecimal(1000L));

        assertEquals(5, result.size());
        assertTrue(!result.isEmpty());
        verify(repository, times(1)).findByCardReqIncomeGreaterThanEqual(new BigDecimal(1000l));
    }

    @DisplayName("Should return a list of blocked cards")
    @Test
    void listBlockedCardsCase01(){
        List<CardEntity> cardEntities = Arrays.asList(
                new CardEntity("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new CardEntity("Test 2", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new CardEntity("Test 3", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new CardEntity("Test 4", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED),
                new CardEntity("Test 5", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED)
        );


        when(modelMapper.map(any(CardEntity.class), eq(Card.class)))
                .thenAnswer( invocationOnMock -> {
                            CardEntity entity = invocationOnMock.getArgument(0);
                            return new Card(entity.getCardName(), entity.getCardBrand(), entity.getCardReqIncome()
                                    , entity.getCardBasicLimit(), entity.getCardStatus());
                        }

                );
        when(repository.findByCardStatus(CardStatus.BLOCKED)).thenReturn(cardEntities);

        List<Card> result = service.listBlockedCards();

        result.forEach(card -> assertTrue(card.getCardStatus().equals(CardStatus.BLOCKED)));
        assertTrue(!result.isEmpty());
        assertEquals(5, result.size());
    }

    @DisplayName("Should return a list of active cards")
    @Test
    void listActiveCardsCase01(){
        List<CardEntity> cardEntities = Arrays.asList(
                new CardEntity("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.ACTIVE),
                new CardEntity("Test 2", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.ACTIVE),
                new CardEntity("Test 3", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.ACTIVE),
                new CardEntity("Test 4", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.ACTIVE),
                new CardEntity("Test 5", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.ACTIVE)
        );


        when(modelMapper.map(any(CardEntity.class), eq(Card.class)))
                .thenAnswer( invocationOnMock -> {
                            CardEntity entity = invocationOnMock.getArgument(0);
                            return new Card(entity.getCardName(), entity.getCardBrand(), entity.getCardReqIncome()
                                    , entity.getCardBasicLimit(), entity.getCardStatus());
                        }

                );
        when(repository.findByCardStatus(CardStatus.ACTIVE)).thenReturn(cardEntities);

        List<Card> result = service.listActiveCards();

        result.forEach(card -> assertTrue(card.getCardStatus().equals(CardStatus.ACTIVE)));
        assertTrue(!result.isEmpty());
        assertEquals(5, result.size());
    }

    @DisplayName("Should return a list of canceled cards")
    @Test
    void listCanceledCardsCase01(){
        List<CardEntity> cardEntities = Arrays.asList(
                new CardEntity("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.CANCELED),
                new CardEntity("Test 2", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.CANCELED),
                new CardEntity("Test 3", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.CANCELED),
                new CardEntity("Test 4", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.CANCELED),
                new CardEntity("Test 5", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.CANCELED)
        );


        when(modelMapper.map(any(CardEntity.class), eq(Card.class)))
                .thenAnswer( invocationOnMock -> {
                            CardEntity entity = invocationOnMock.getArgument(0);
                            return new Card(entity.getCardName(), entity.getCardBrand(), entity.getCardReqIncome()
                                    , entity.getCardBasicLimit(), entity.getCardStatus());
                        }

                );
        when(repository.findByCardStatus(CardStatus.CANCELED)).thenReturn(cardEntities);

        List<Card> result = service.listCanceledCards();

        result.forEach(card -> assertTrue(card.getCardStatus().equals(CardStatus.CANCELED)));
        assertTrue(!result.isEmpty());
        assertEquals(5, result.size());
    }

    @DisplayName("Should block card when card status id active")
    @Test
    void blockCardCase01(){
        Card activeCard = new Card("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.ACTIVE);
        CardEntity entity = createCardEntity(activeCard);

        when(repository.findByCardName("Test")).thenReturn(entity);

        service.blockCard(activeCard);

        verify(repository, times(1)).findByCardName("Test");
        verify(repository, times(1)).save(entity);

        assertTrue(CardStatus.BLOCKED.equals(entity.getCardStatus()));
    }

    @DisplayName("Should block card when card status is canceled")
    @Test
    void blockCardCase02(){
        Card activeCard = new Card("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.CANCELED);
        CardEntity entity = createCardEntity(activeCard);

        when(repository.findByCardName("Test")).thenReturn(entity);

        service.blockCard(activeCard);

        verify(repository, times(1)).findByCardName("Test");
        verify(repository, times(1)).save(entity);

        assertTrue(CardStatus.BLOCKED.equals(entity.getCardStatus()));
    }



    @DisplayName("Should active card when card is blocked")
    @Test
    void activeCardCase01(){
        Card activeCard = new Card("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED);
        CardEntity entity = createCardEntity(activeCard);

        when(repository.findByCardName("Test")).thenReturn(entity);

        service.activateCard(activeCard);

        verify(repository, times(1)).findByCardName("Test");
        verify(repository, times(1)).save(entity);

        assertTrue(CardStatus.ACTIVE.equals(entity.getCardStatus()));
    }

    @DisplayName("Should active card when card is canceled")
    @Test
    void activeCardCase02(){
        Card activeCard = new Card("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.CANCELED);
        CardEntity entity = createCardEntity(activeCard);

        when(repository.findByCardName("Test")).thenReturn(entity);

        service.activateCard(activeCard);

        verify(repository, times(1)).findByCardName("Test");
        verify(repository, times(1)).save(entity);

        assertTrue(CardStatus.ACTIVE.equals(entity.getCardStatus()));
    }

    @DisplayName("Should cancel card when card is blocked")
    @Test
    void cancelCardCase01(){
        Card activeCard = new Card("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.BLOCKED);
        CardEntity entity = createCardEntity(activeCard);

        when(repository.findByCardName("Test")).thenReturn(entity);

        service.cancelCard(activeCard);

        verify(repository, times(1)).findByCardName("Test");
        verify(repository, times(1)).save(entity);

        assertTrue(CardStatus.CANCELED.equals(entity.getCardStatus()));
    }

    @DisplayName("Should cancel card when card is active")
    @Test
    void cancelCardCase02(){
        Card activeCard = new Card("Test", CardBrand.MASTERCARD, new BigDecimal("1000"), new BigDecimal("1000"), CardStatus.ACTIVE);
        CardEntity entity = createCardEntity(activeCard);

        when(repository.findByCardName("Test")).thenReturn(entity);

        service.cancelCard(activeCard);

        verify(repository, times(1)).findByCardName("Test");
        verify(repository, times(1)).save(entity);

        assertTrue(CardStatus.CANCELED.equals(entity.getCardStatus()));
    }
}