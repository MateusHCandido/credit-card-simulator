package com.github.MateusHCandido.card_generating_service.domain.object;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestDataCardIssuance {
    private String cardName;
    private String customerCpf;
    private String customerAddress;
    private BigDecimal cardLimitApproved;
}
