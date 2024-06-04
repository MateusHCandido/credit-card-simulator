package com.github.MateusHCandido.customer_service.application.usecase;

import com.github.MateusHCandido.customer_service.application.gateway.AppCustomerRepository;
import com.github.MateusHCandido.customer_service.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerUseCaseTest {

    @Mock
    private AppCustomerRepository repository;

    @InjectMocks
    private CustomerUseCase useCase;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test()
    @DisplayName("should create a customer")
    void useCaseInMethodCreateCustomer(){
        Customer customer = new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17"));
        when(repository.createCustomer(customer)).thenReturn(customer);

        Customer createdCustomer = useCase.createCustomer(customer);

        assertNotNull(createdCustomer);
        assertEquals("Mateus Henrique", createdCustomer.getCustomerName());
        verify(repository, times(1)).createCustomer(customer);
    }

    @Test
    @DisplayName("Should return a list with three customers")
    void useCaseInMethodListAllCustomers(){
        List<Customer> customers = Arrays.asList(
                new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")),
                new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")),
                new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17"))
        );

        when(repository.listAllCustomers()).thenReturn(customers);

        List<Customer> result = repository.listAllCustomers();

        assertEquals(3, result.size());
        verify(repository, times(1)).listAllCustomers();
    }

    @Test
    @DisplayName("Should update a customer")
    void useCaseInMethodUpdateCustomer(){
        String name = "Mateus Henrique";
        String cpf = "111.123.456-10";
        String email = "mateushcandido5@gmail.com";

        Customer customer = new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17"));

        when(repository.updateCustomer(cpf, email, name)).thenReturn(customer);

        Customer customerUpdated = repository.updateCustomer(cpf, email, name);

        assertTrue(name.equals(customerUpdated.getCustomerName()));
        assertTrue(cpf.equals(customerUpdated.getCustomerCpf()));
        assertTrue(email.equals(customerUpdated.getCustomerEmail()));
        verify(repository, times(1)).updateCustomer(cpf, email, name);
    }

    @Test
    @DisplayName("Should return a customer when the cpf param is sent")
    void useCaseInMethodGetCustomerByCpf(){
        String cpf = "111.123.456-10";

        Customer customer = new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17"));

        when(repository.getCustomerByCpf(cpf)).thenReturn(customer);

        Customer customerFounded = repository.getCustomerByCpf(cpf);

        assertTrue(cpf.equals(customerFounded.getCustomerCpf()));
        verify(repository, times(1)).getCustomerByCpf(cpf);

    }
}