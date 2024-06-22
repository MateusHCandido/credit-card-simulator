package com.github.MateusHCandido.validation_credit_service.application.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovedCard {

    private String cardName;
    private String cardBrand;

    private BigDecimal approvedLimit;
}
