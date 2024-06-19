package com.github.MateusHCandido.card_generating_service.infra.gateway;

import com.github.MateusHCandido.card_generating_service.application.gateway.AppCardRepository;
import com.github.MateusHCandido.card_generating_service.domain.CardStatus;
import com.github.MateusHCandido.card_generating_service.domain.entities.Card;
import com.github.MateusHCandido.card_generating_service.infra.persistence.CardEntity;
import com.github.MateusHCandido.card_generating_service.infra.persistence.CardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardRepositoryJpa implements AppCardRepository {

    @Autowired
    private CardRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Card saveCard(Card card) {
        CardEntity entity = modelMapper.map(card, CardEntity.class);
        repository.save(entity);

        return modelMapper.map(entity, Card.class);
    }

    @Override
    public List<Card> findByCardReqIncomeLessThanEqual(BigDecimal cardReqIncome) {

        return repository.findByCardReqIncomeLessThanEqual(cardReqIncome).stream()
                .map(cardEntity -> modelMapper.map(cardEntity, Card.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> findByCardReqIncomeGreaterThanEqual(BigDecimal cardReqIncome) {
        return repository.findByCardReqIncomeGreaterThanEqual(cardReqIncome).stream()
                .map(cardEntity -> modelMapper.map(cardEntity, Card.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> listBlockedCards() {
        return repository.findByCardStatus(CardStatus.BLOCKED)
                .stream()
                .map(cardEntity -> modelMapper.map(cardEntity, Card.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> listActiveCards() {
        return repository.findByCardStatus(CardStatus.ACTIVE)
                .stream()
                .map(cardEntity -> modelMapper.map(cardEntity, Card.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> listCanceledCards() {
        return repository.findByCardStatus(CardStatus.CANCELED)
                .stream()
                .map(cardEntity -> modelMapper.map(cardEntity, Card.class))
                .collect(Collectors.toList());
    }

    @Override
    public void blockCard(Card card) {
        CardEntity entity = repository.findByCardName(card.getCardName());
        entity.setCardStatus(CardStatus.BLOCKED);
        repository.save(entity);
    }

    @Override
    public void activateCard(Card card) {
        CardEntity entity = repository.findByCardName(card.getCardName());
        entity.setCardStatus(CardStatus.ACTIVE);
        repository.save(entity);
    }

    @Override
    public Card findCardByCardName(String cardName) {
        CardEntity entity = repository.findByCardName(cardName);
        return modelMapper.map(entity, Card.class);
    }

    @Override
    public void cancelCard(Card card) {
        CardEntity entity = repository.findByCardName(card.getCardName());
        entity.setCardStatus(CardStatus.CANCELED);
        repository.save(entity);
    }


}
