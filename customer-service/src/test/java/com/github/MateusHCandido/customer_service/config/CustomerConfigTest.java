package com.github.MateusHCandido.customer_service.config;

import com.github.MateusHCandido.customer_service.application.gateway.AppCustomerRepository;
import com.github.MateusHCandido.customer_service.application.usecase.CustomerUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CustomerConfigTest.Config.class})
class CustomerConfigTest {

    @Configuration
    static class Config{
        @MockBean
        private AppCustomerRepository appCustomerRepository;

        @Bean
        public CustomerUseCase customerUseCase(){
            return new CustomerUseCase((appCustomerRepository));
        }
    }

    @Autowired
    private CustomerUseCase customerUseCase;

    @Test
    void testCustomerUseCaseBean(){
        assertNotNull(customerUseCase);
    }
}