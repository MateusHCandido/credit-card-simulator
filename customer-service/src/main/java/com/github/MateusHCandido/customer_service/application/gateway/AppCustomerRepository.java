package com.github.MateusHCandido.customer_service.application.gateway;

import com.github.MateusHCandido.customer_service.domain.Customer;

import java.util.List;

public interface AppCustomerRepository {

    Customer createCustomer(Customer customer);

    List<Customer> listAllCustomers();

    Customer updateCustomer(String customerCpf, String customerEmail, String customerName);

    Customer getCustomerByCpf(String customerCpf);
}
