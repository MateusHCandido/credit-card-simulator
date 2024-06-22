package com.github.MateusHCandido.card_generating_service.application.gateway;

import com.github.MateusHCandido.card_generating_service.domain.object.CustomerCard;

import java.util.List;

public interface AppCustomerCardRepository {

    List<CustomerCard> findByCustomerCpf(String customerCpf);
}
