package com.github.MateusHCandido.customer_service.domain;


import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class Customer {
    private String customerName;
    private String customerCpf;
    private String customerEmail;
    private LocalDate customerBirthdate;

    public Customer(){}

    public Customer(String customerName, String customerCpf, String customerEmail, LocalDate customerBirthdate){
        isCpfValid(customerCpf);
        isEmailValid(customerEmail);
        isAdult(customerBirthdate);
        this.customerName = customerName;
        this.customerCpf = customerCpf;
        this.customerEmail = customerEmail;
        this.customerBirthdate = customerBirthdate;
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

    public LocalDate getCustomerBirthdate() {
        return customerBirthdate;
    }

    protected void isCpfValid(String customerCpf){
        String cpfPattern = "\\d{3}\\.\\d{3}.\\d{3}-\\d{2}";
        boolean cpfValid = Pattern.matches(cpfPattern, customerCpf);

        if (!cpfValid){
            throw new IllegalArgumentException("O cpf fornecido não é válido.");
        }
    }

    protected void isEmailValid(String customerEmail){
        String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        boolean emailValid = Pattern.matches(emailPattern, customerEmail);

        if (!emailValid){
            throw new IllegalArgumentException("O email fornecido não é válido.");
        }
    }

    protected void isAdult(LocalDate customerBirthdate){
        boolean customerIsAdult = Period.between(customerBirthdate, LocalDate.now()).getYears() > 18;

        if (!customerIsAdult){
            throw new IllegalArgumentException("O cliente é menor de idade, não pode se cadastar.");
        }
    }
}
