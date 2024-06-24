package com.github.MateusHCandido.card_generating_service.infra.gateway;

import com.github.MateusHCandido.card_generating_service.application.gateway.AppCustomerCardRepository;
import com.github.MateusHCandido.card_generating_service.domain.object.CustomerCard;
import com.github.MateusHCandido.card_generating_service.infra.persistence.CustomerCardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerCardRepositoryJpa implements AppCustomerCardRepository {

    @Autowired
    private CustomerCardRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<CustomerCard> findByCustomerCpf(String customerCpf) {
        return repository.findByCustomerCpf(customerCpf).stream()
                .map(customerCardEntity -> modelMapper.map(customerCardEntity, CustomerCard.class))
                .collect(Collectors.toList());
    }
}
