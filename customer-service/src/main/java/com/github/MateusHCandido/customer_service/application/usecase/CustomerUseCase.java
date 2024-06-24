package com.github.MateusHCandido.customer_service.application.usecase;

import com.github.MateusHCandido.customer_service.application.gateway.AppCustomerRepository;
import com.github.MateusHCandido.customer_service.domain.Customer;

import java.util.List;

public class CustomerUseCase{

    private final AppCustomerRepository customerRepository;

    public CustomerUseCase(AppCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer createCustomer(Customer customer){return customerRepository.createCustomer(customer);}

    public List<Customer> listAllCustomer(){
        return customerRepository.listAllCustomers();
    }

    public Customer updateCustomer(String customerCpf, String customerEmail, String customerName){
        return customerRepository.updateCustomer(customerCpf, customerEmail, customerName);
    }

    public Customer findCustomerByCpf(String customerCpf){
        return customerRepository.getCustomerByCpf(customerCpf);
    }
}
