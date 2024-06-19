package com.github.MateusHCandido.card_generating_service.config;

import com.github.MateusHCandido.card_generating_service.domain.CardStatus;
import com.github.MateusHCandido.card_generating_service.domain.entities.Card;
import com.github.MateusHCandido.card_generating_service.infra.controller.dto.GetCardDto;
import com.github.MateusHCandido.card_generating_service.infra.controller.dto.PostCardDto;
import com.github.MateusHCandido.card_generating_service.infra.persistence.CardEntity;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardEntityMapper {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();



        modelMapper.addConverter(new AbstractConverter<Card, CardEntity>() {

            @Override
            protected CardEntity convert(Card source) {
                return new CardEntity(source);
            }
        });

        modelMapper.addConverter(new AbstractConverter<CardEntity, Card>() {

            @Override
            protected Card convert(CardEntity source) {
                return new Card(
                        source.getCardName(),
                        source.getCardBrand(),
                        source.getCardReqIncome(),
                        source.getCardBasicLimit(),
                        source.getCardStatus()
                );
            }
        });

        modelMapper.addConverter(new AbstractConverter<CardEntity, GetCardDto>() {

            @Override
            protected GetCardDto convert(CardEntity source) {
                return new GetCardDto(
                        source.getCardName(),
                        source.getCardBrand(),
                        source.getCardReqIncome(),
                        source.getCardBasicLimit(),
                        source.getCardStatus()
                );
            }
        });

        modelMapper.addConverter(new AbstractConverter<Card, GetCardDto>() {

            @Override
            protected GetCardDto convert(Card source) {
                return new GetCardDto(
                        source.getCardName(),
                        source.getCardBrand(),
                        source.getCardReqIncome(),
                        source.getCardBasicLimit(),
                        source.getCardStatus()
                );
            }
        });

        return modelMapper;
    }
}
