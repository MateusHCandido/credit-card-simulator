package com.github.MateusHCandido.validation_credit_service.application.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestDataCardIssuance {
    private String cardName;
    private String customerCpf;
    private String customerAddress;
    private BigDecimal cardLimitApproved;
}
