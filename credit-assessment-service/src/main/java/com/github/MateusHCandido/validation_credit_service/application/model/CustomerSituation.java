package com.github.MateusHCandido.validation_credit_service.application.model;

import com.github.MateusHCandido.validation_credit_service.infra.clients.customer.CustomerCard;
import com.github.MateusHCandido.validation_credit_service.infra.controller.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSituation {
    private CustomerDto customer;
    private List<CustomerCard> cards;

    public CustomerSituation(CustomerDto customer) {
        this.customer = customer;
    }
}
