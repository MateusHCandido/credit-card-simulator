package com.github.MateusHCandido.customer_service.infra.controller.dto;

public class CustomerPutDto {

    private String customerName;
    private String customerCpf;
    private String customerEmail;

    public CustomerPutDto(String customerName, String customerCpf, String customerEmail) {
        this.customerName = customerName;
        this.customerCpf = customerCpf;
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerCpf() {
        return customerCpf;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
}
