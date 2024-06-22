package com.github.MateusHCandido.card_generating_service.application.gateway;

import com.github.MateusHCandido.card_generating_service.domain.object.CustomerCard;

import java.util.List;

public class CustomerCardUseCase {


    private final AppCustomerCardRepository repository;

    public CustomerCardUseCase(AppCustomerCardRepository repository) {
        this.repository = repository;
    }


    public List<CustomerCard> findCardCustomerByCpf(String customerCpf){
        return repository.findByCustomerCpf(customerCpf);
    }
}
