package com.github.MateusHCandido.card_generating_service.application.gateway;

import com.github.MateusHCandido.card_generating_service.application.gateway.exception.*;
import com.github.MateusHCandido.card_generating_service.domain.enums.CardBrand;
import com.github.MateusHCandido.card_generating_service.domain.enums.CardStatus;
import com.github.MateusHCandido.card_generating_service.domain.entities.Card;
import com.github.MateusHCandido.card_generating_service.domain.entities.CardFactory;

import java.math.BigDecimal;
import java.util.List;

public class CardUseCase{

    private final AppCardRepository repository;

    private final CardFactory factory;


    public CardUseCase(AppCardRepository repository, CardFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }


    public Card saveCard(String name, String cardBrand, String reqIncome, String basicLimit){
        try{
            CardBrand brand = CardBrand.valueOf(cardBrand.toUpperCase());
            Card card = new Card();
            if(brand.equals(CardBrand.MASTERCARD)){
                card = factory.generateBlockCardWithMastercardFlag(name, new BigDecimal(reqIncome),new BigDecimal(basicLimit));
            } else if (brand.equals(CardBrand.VISA)){
                card = factory.generateActiveCardWithVisaFlag(name, new BigDecimal(reqIncome),new BigDecimal(basicLimit));
            }
            repository.saveCard( card );
            return card;
        } catch (IllegalArgumentException argumentException){
            throw new IllegalArgumentException("Não foi possível criar cartão. Verifique se o campo foi preenchido corretamente");
        }

    }

    public List<Card> findByCardReqIncomeLessThanEqual(Long cardReqIncome){
        return repository.findByCardReqIncomeLessThanEqual(new BigDecimal(String.valueOf(cardReqIncome)));
    }

    public List<Card> findByCardReqIncomeGreaterThanEqual(Long cardReqIncome){
        return repository.findByCardReqIncomeGreaterThanEqual(new BigDecimal(cardReqIncome));
    }

    public List<Card> listBlockedCard(){
        return repository.listBlockedCards();
    }

    public List<Card> listActivateCard(){
        return repository.listActiveCards();
    }

    public List<Card> listCanceledCards(){
        return repository.listCanceledCards();
    }

    public void blockCard(String cardName) throws CardAlreadyBlockedException {
        Card card = repository.findCardByCardName(cardName);
        if(card.getCardStatus().equals(CardStatus.BLOCKED)){
            throw new CardAlreadyBlockedException("O cartão já se encontra bloqueado.");
        }

        card.setCardStatus(CardStatus.BLOCKED);
        repository.blockCard(card);
    }

    public void activateCard(String cardName) throws CardAlreadyActiveException {
        Card card = repository.findCardByCardName(cardName);
        if (card.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new CardAlreadyActiveException("O cartão já se encontra ativo.");
        }

        card.setCardStatus(CardStatus.ACTIVE);
        repository.activateCard(card);
    }

    public void cancelCard(String cardName) throws CardAlreadyCanceledException{
        Card card = repository.findCardByCardName(cardName);
        if(card.getCardStatus().equals(CardStatus.CANCELED)){
            throw new CardAlreadyCanceledException("O cartão já se encontra cancelado.");
        }

        card.setCardStatus(CardStatus.CANCELED);
        repository.cancelCard(card);
    }
}
