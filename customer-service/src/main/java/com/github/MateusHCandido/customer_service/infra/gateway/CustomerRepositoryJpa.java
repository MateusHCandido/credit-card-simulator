package com.github.MateusHCandido.customer_service.infra.gateway;

import com.github.MateusHCandido.customer_service.application.gateway.AppCustomerRepository;
import com.github.MateusHCandido.customer_service.domain.Customer;
import com.github.MateusHCandido.customer_service.infra.persistence.CustomerEntity;
import com.github.MateusHCandido.customer_service.infra.persistence.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerRepositoryJpa implements AppCustomerRepository {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Customer createCustomer(Customer customer) {
        CustomerEntity entity = modelMapper.map(customer, CustomerEntity.class);
        CustomerEntity savedEntity = repository.save(entity);
        return modelMapper.map(savedEntity, Customer.class);
    }

    @Override
    public List<Customer> listAllCustomers() {
        return repository.findAll()
                .stream()
                .map(customerEntity -> modelMapper.map(customerEntity, Customer.class))
                .collect(Collectors.toList());
    }

    @Override
    public Customer updateCustomer(String customerCpf, String customerEmailForUpdate, String customerNameForUpdate) {
        boolean cpfExists = repository.existsByCustomerCpf(customerCpf);
        if (cpfExists){

            Optional<CustomerEntity> entity = Optional.ofNullable(repository.findByCustomerCpf(customerCpf));
            if (entity.isPresent()){
                CustomerEntity customer = entity.get();

                customer.setCustomerEmail(customerEmailForUpdate);
                customer.setCustomerName(customerNameForUpdate);
                repository.save(customer);

                return modelMapper.map(customer, Customer.class);
            }

        } throw new EntityNotFoundException("Cliente não encontrado");
    }

    @Override
    public Customer getCustomerByCpf(String customerCpf) {
        Optional<CustomerEntity> entity = Optional.ofNullable(repository.findByCustomerCpf(customerCpf));

        if(entity.isPresent()){
            CustomerEntity customer = entity.get();
            return modelMapper.map(customer, Customer.class);
        } throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
    }
}
