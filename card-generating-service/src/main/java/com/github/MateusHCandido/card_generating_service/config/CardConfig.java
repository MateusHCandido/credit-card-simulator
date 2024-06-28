package com.github.MateusHCandido.card_generating_service.config;

import com.github.MateusHCandido.card_generating_service.application.gateway.AppCardRepository;
import com.github.MateusHCandido.card_generating_service.application.gateway.CardUseCase;

import com.github.MateusHCandido.card_generating_service.domain.entities.CardFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardConfig {

    @Bean
    CardUseCase cardUseCase(AppCardRepository appCardRepository, CardFactory cardFactory){
        return new CardUseCase(appCardRepository, cardFactory);
    }

    @Bean
    CardFactory cardFactory( ){
        return new CardFactory();
    }



}
