package com.github.MateusHCandido.card_generating_service.config;

import com.github.MateusHCandido.card_generating_service.application.gateway.AppCustomerCardRepository;
import com.github.MateusHCandido.card_generating_service.application.gateway.CustomerCardUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerCardConfig {

    @Bean
    public CustomerCardUseCase useCase(AppCustomerCardRepository repository){
        return new CustomerCardUseCase(repository);
    }
}
