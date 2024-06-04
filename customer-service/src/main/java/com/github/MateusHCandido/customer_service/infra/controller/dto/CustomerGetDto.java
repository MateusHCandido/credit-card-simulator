package com.github.MateusHCandido.customer_service.infra.controller.dto;

public class CustomerGetDto {

    private String customerCpf;

    public CustomerGetDto(){}
    public CustomerGetDto(String customerCpf) {
        this.customerCpf = customerCpf;
    }

    public String getCustomerCpf() {
        return customerCpf;
    }
}
