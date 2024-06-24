package com.github.MateusHCandido.card_generating_service.config;

import com.github.MateusHCandido.card_generating_service.application.gateway.AppCardRepository;
import com.github.MateusHCandido.card_generating_service.application.gateway.CardUseCase;
import com.github.MateusHCandido.card_generating_service.domain.entities.CardFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CardConfigTest.Config.class})
class CardConfigTest {

    @Configuration
    static class Config{
        @MockBean
        private AppCardRepository appCardRepository;

        @Mock
        private CardFactory factory;

        @Bean
        CardUseCase cardUseCase() {return new CardUseCase(appCardRepository, factory);}
    }

    @Autowired
    private CardUseCase cardUseCase;

    @Test
    void testCardUseCaseBean(){
        assertNotNull(cardUseCase);
    }
}