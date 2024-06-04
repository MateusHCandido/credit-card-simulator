package com.github.MateusHCandido.customer_service.infra.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerDto {
    private String customerName;
    private String customerCpf;
    private String customerEmail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate customerBirthdate;

    public CustomerDto(){}
    public CustomerDto(String customerName, String customerCpf, String customerEmail, LocalDate customerBirthdate) {
        this.customerName = customerName;
        this.customerCpf = customerCpf;
        this.customerEmail = customerEmail;
        this.customerBirthdate = customerBirthdate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCpf() {
        return customerCpf;
    }

    public void setCustomerCpf(String customerCpf) {
        this.customerCpf = customerCpf;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LocalDate getCustomerBirthdate() {
        return customerBirthdate;
    }

    public void setCustomerBirthdate(LocalDate customerBirthdate) {
        this.customerBirthdate = customerBirthdate;
    }
}
