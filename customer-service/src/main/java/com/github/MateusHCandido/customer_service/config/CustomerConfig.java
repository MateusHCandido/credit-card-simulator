package com.github.MateusHCandido.customer_service.config;

import com.github.MateusHCandido.customer_service.application.gateway.AppCustomerRepository;
import com.github.MateusHCandido.customer_service.application.usecase.CustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CustomerConfig {

    @Bean
    CustomerUseCase customerUseCase(AppCustomerRepository appCustomerRepository){
        return new CustomerUseCase(appCustomerRepository);
    }


}
