package com.github.MateusHCandido.card_generating_service.infra.persistence;


import com.github.MateusHCandido.card_generating_service.domain.CardStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findByCardReqIncomeLessThanEqual(BigDecimal cardReqIncome);

    List<CardEntity> findByCardReqIncomeGreaterThanEqual(BigDecimal cardReqIncome);

    List<CardEntity> findByCardStatus(CardStatus status);

    CardEntity findByCardName(String cardName);
}
