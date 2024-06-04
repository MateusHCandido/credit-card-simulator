package com.github.MateusHCandido.customer_service.infra.controller;

import com.github.MateusHCandido.customer_service.application.usecase.CustomerUseCase;
import com.github.MateusHCandido.customer_service.domain.Customer;
import com.github.MateusHCandido.customer_service.infra.controller.dto.CustomerDto;
import com.github.MateusHCandido.customer_service.infra.controller.dto.CustomerGetDto;
import com.github.MateusHCandido.customer_service.infra.controller.dto.CustomerPutDto;
import com.github.MateusHCandido.customer_service.infra.gateway.CustomerRepositoryJpa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerUseCase customerUseCase;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody CustomerDto dto){
        Customer customer = modelMapper.map(dto, Customer.class);
        Customer saved = customerUseCase.createCustomer(customer);
        return modelMapper.map(saved, CustomerDto.class);
    }

    @GetMapping("/list-all")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> listAllCustomers(){
        return customerUseCase.listAllCustomer()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto findCustomerByCpf(@RequestBody CustomerGetDto dto){
        Customer customer = customerUseCase.findCustomerByCpf(dto.getCustomerCpf());
        return modelMapper.map(customer, CustomerDto.class);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@RequestBody CustomerPutDto dto){
        Customer updated = customerUseCase.updateCustomer(dto.getCustomerCpf(), dto.getCustomerEmail(), dto.getCustomerName());
        return modelMapper.map(updated, CustomerDto.class);
    }
}
