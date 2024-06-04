package com.github.MateusHCandido.customer_service.config;

import com.github.MateusHCandido.customer_service.domain.Customer;
import com.github.MateusHCandido.customer_service.infra.controller.dto.CustomerDto;
import com.github.MateusHCandido.customer_service.infra.persistence.CustomerEntity;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class CustomerEntityMapper {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                return LocalDate.parse(source, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
        });


        modelMapper.addConverter(new AbstractConverter<Customer, CustomerDto>() {
            @Override
            protected CustomerDto convert(Customer source) {
                CustomerDto destination = new CustomerDto();
                destination.setCustomerName(source.getCustomerName());
                destination.setCustomerCpf(source.getCustomerCpf());
                destination.setCustomerEmail(source.getCustomerEmail());
                destination.setCustomerBirthdate(source.getCustomerBirthdate());
                return destination;
            }
        });


        modelMapper.addConverter(new AbstractConverter<CustomerDto, Customer>() {
            @Override
            protected Customer convert(CustomerDto source) {
                Customer destination = new Customer(source.getCustomerName(),
                        source.getCustomerCpf(),
                        source.getCustomerEmail(),
                        source.getCustomerBirthdate());
                return destination;
            }

        });

        modelMapper.addConverter(new AbstractConverter<Customer, CustomerEntity>() {
            @Override
            protected CustomerEntity convert(Customer source) {
                CustomerEntity destination = new CustomerEntity(source.getCustomerName(),
                        source.getCustomerCpf(),
                        source.getCustomerEmail(),
                        source.getCustomerBirthdate());
                return destination;
            }
        });

        modelMapper.addConverter(new AbstractConverter<CustomerEntity, Customer>() {
            @Override
            protected Customer convert(CustomerEntity source) {
                Customer destination = new Customer(source.getCustomerName(),
                        source.getCustomerCpf(),
                        source.getCustomerEmail(),
                        source.getCustomerBirthdate());
                return destination;
            }
        });

        return modelMapper;
    }
}
