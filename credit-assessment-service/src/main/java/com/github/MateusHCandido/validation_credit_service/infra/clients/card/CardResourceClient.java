package com.github.MateusHCandido.validation_credit_service.infra.clients.card;

import com.github.MateusHCandido.validation_credit_service.infra.clients.customer.CustomerCard;
import com.github.MateusHCandido.validation_credit_service.infra.controller.dto.CustomerGetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "card-generating-service", path = "/cards")
public interface CardResourceClient {


    @PostMapping("/find-cards-by-customer")
    public ResponseEntity<List<CustomerCardDto>> listCardByCustomer(@RequestBody CustomerGetDto dto);

    @GetMapping("/list-cards-with-required-income-less-or-equal/{reqIncome}")
    public ResponseEntity<List<GetCardDto>> findByCardReqIncomeLessThanEqual(@PathVariable Long reqIncome);
}
