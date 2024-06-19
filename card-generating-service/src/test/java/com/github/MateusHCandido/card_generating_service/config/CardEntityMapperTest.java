package com.github.MateusHCandido.card_generating_service.config;

import com.github.MateusHCandido.card_generating_service.domain.CardBrand;
import com.github.MateusHCandido.card_generating_service.domain.CardStatus;
import com.github.MateusHCandido.card_generating_service.domain.entities.Card;
import com.github.MateusHCandido.card_generating_service.infra.controller.dto.GetCardDto;
import com.github.MateusHCandido.card_generating_service.infra.controller.dto.PostCardDto;
import com.github.MateusHCandido.card_generating_service.infra.persistence.CardEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CardEntityMapper.class})
class CardEntityMapperTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void testModelMapperBean(){
        assertNotNull(modelMapper);
    }

    @Test
    void testConversionCardDomainToCardEntity(){
        Card domain = new Card("Test", CardBrand.MASTERCARD, new BigDecimal("1000"),new BigDecimal("1000"), CardStatus.ACTIVE);
        CardEntity entity = modelMapper.map(domain, CardEntity.class);

        assertNotNull(entity);
        assertTrue(CardBrand.MASTERCARD.equals(entity.getCardBrand()));
    }

    @Test
    void testConversionCardEntityToCardDomain(){
        CardEntity entity = new CardEntity("Test", CardBrand.MASTERCARD, new BigDecimal("1000"),new BigDecimal("1000"), CardStatus.ACTIVE);
        Card domain = modelMapper.map(entity, Card.class);

        assertNotNull(domain);
        assertTrue(CardBrand.MASTERCARD.equals(domain.getCardBrand()));
    }

    @Test
    void testConversionCardEntityToCardDto(){
        CardEntity entity = new CardEntity("Test", CardBrand.MASTERCARD, new BigDecimal("1000"),new BigDecimal("1000"), CardStatus.ACTIVE);
        GetCardDto dto = modelMapper.map(entity, GetCardDto.class);

        assertNotNull(dto);
        assertTrue("MASTERCARD".equals(dto.getCardBrand()));
    }
}