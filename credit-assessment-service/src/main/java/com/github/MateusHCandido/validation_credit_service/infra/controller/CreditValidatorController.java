package com.github.MateusHCandido.validation_credit_service.infra.controller;

import com.github.MateusHCandido.validation_credit_service.application.model.CustomerCreditAssessment;
import com.github.MateusHCandido.validation_credit_service.application.model.CustomerSituation;


import com.github.MateusHCandido.validation_credit_service.application.model.RequestDataCardIssuance;
import com.github.MateusHCandido.validation_credit_service.infra.controller.dto.CustomerGetDto;
import com.github.MateusHCandido.validation_credit_service.infra.gateway.CreditValidatorService;
import com.github.MateusHCandido.validation_credit_service.infra.gateway.exception.CardRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-assessments")
public class CreditValidatorController {

    @Autowired
    private CreditValidatorService creditValidatorService;

    @PostMapping("/check-customer-situation")
    public ResponseEntity<CustomerSituation> checkCustomerSituation(@RequestBody CustomerGetDto dto){
        return ResponseEntity.ok(creditValidatorService.checkCustomerSituation(dto.getCustomerCpf()));
    }

    @PostMapping("/evaluate-credit")
    public ResponseEntity evaluateCredit(@RequestBody CustomerCreditAssessment request){
        return  ResponseEntity.ok(creditValidatorService.evaluateCredit(request.getCustomerCpf(), request.getCustomerIncome()));
    }


    @PostMapping("/request-card")
    public ResponseEntity requestCard(@RequestBody RequestDataCardIssuance request){
        try{
            return ResponseEntity.ok(creditValidatorService.requestCardIssuance(request));
        }catch (CardRequestException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
