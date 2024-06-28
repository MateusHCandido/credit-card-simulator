package com.github.MateusHCandido.validation_credit_service.infra.clients.customer;

import com.github.MateusHCandido.validation_credit_service.infra.controller.dto.CustomerDto;
import com.github.MateusHCandido.validation_credit_service.infra.controller.dto.CustomerGetDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "customer-service", path = "/customers")
public interface CustomerResourceClient {

    @PostMapping("/find")
    ResponseEntity<CustomerDto> findCustomerByCpf(@RequestBody CustomerGetDto dto);
}
