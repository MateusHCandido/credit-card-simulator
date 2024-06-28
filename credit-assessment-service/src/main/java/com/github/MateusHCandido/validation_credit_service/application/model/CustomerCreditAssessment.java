package com.github.MateusHCandido.validation_credit_service.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreditAssessment {

    private String customerCpf;
    private Long customerIncome;
}
