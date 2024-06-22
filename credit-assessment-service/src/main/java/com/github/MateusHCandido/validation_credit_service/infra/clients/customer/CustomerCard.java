package com.github.MateusHCandido.validation_credit_service.infra.clients.customer;

import com.github.MateusHCandido.validation_credit_service.application.model.enums.CardBrand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerCard {
    private String cardName;
    private CardBrand cardBrand;
    private BigDecimal availableLimit;
}
