package com.github.MateusHCandido.customer_service.config;


import com.github.MateusHCandido.customer_service.domain.Customer;
import com.github.MateusHCandido.customer_service.infra.controller.dto.CustomerDto;
import com.github.MateusHCandido.customer_service.infra.persistence.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CustomerEntityMapper.class})
class CustomerEntityMapperTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void testModelMapperBean(){
        assertNotNull(modelMapper);
    }

    @Test
    void testStringToLocalDateConversion(){
        String dateStr = "03/06/2024";
        LocalDate expectedDate = LocalDate.of(2024, 06, 03);
        LocalDate convertedDate = modelMapper.map(dateStr, LocalDate.class);

        assertEquals(expectedDate, convertedDate);
    }

    @Test
    void testDtoToDomainConversion(){
        CustomerDto dto = new CustomerDto("Mateus", "123.456.789-10", "mateus@teste.com", LocalDate.of(1999,10,17));
        Customer customer = modelMapper.map(dto, Customer.class);

        assertNotNull(customer);
        assertTrue("Mateus".equals(customer.getCustomerName()));
    }

    @Test
    void testDomainToDtoConversion(){
        Customer customer = new Customer("Mateus", "123.456.789-10", "mateus@teste.com", LocalDate.parse("1999-10-17"));
        CustomerDto dto = modelMapper.map(customer, CustomerDto.class);

        assertNotNull(dto);
        assertTrue("Mateus".equals(dto.getCustomerName()));
    }

    @Test
    void testDomainToEntity(){
        Customer customer = new Customer("Mateus", "123.456.789-10", "mateus@teste.com", LocalDate.parse("1999-10-17"));
        CustomerEntity entity = modelMapper.map(customer, CustomerEntity.class);

        assertNotNull(entity);
        assertTrue("Mateus".equals(entity.getCustomerName()));
    }

    @Test
    void testEntityToDomain(){
        CustomerEntity entity = new CustomerEntity("Mateus", "123.456.789-10", "mateus@teste.com", LocalDate.parse("1999-10-17"));
        Customer customer = modelMapper.map(entity, Customer.class);

        assertNotNull(customer);
        assertTrue("Mateus".equals(customer.getCustomerName()));
    }

}