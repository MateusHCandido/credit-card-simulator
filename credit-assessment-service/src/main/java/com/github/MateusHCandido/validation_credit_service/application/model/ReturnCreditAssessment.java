package com.github.MateusHCandido.validation_credit_service.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnCreditAssessment {

    private List<ApprovedCard> approvedCards;
}
