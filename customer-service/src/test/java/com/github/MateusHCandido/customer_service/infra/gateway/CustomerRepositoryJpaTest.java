package com.github.MateusHCandido.customer_service.infra.gateway;

import com.github.MateusHCandido.customer_service.domain.Customer;
import com.github.MateusHCandido.customer_service.infra.persistence.CustomerEntity;
import com.github.MateusHCandido.customer_service.infra.persistence.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryJpaTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerRepositoryJpa repositoryJpa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private CustomerEntity createCustomerEntity(Customer customer){
        return new CustomerEntity(
                customer.getCustomerName(),
                customer.getCustomerCpf(),
                customer.getCustomerEmail(),
                customer.getCustomerBirthdate()
        );
    }

    @Test
    @DisplayName("Should create a customer with success and return a customer object of domain layer")
    void createCustomerCase1(){
        Customer customer = new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17"));
        CustomerEntity customerEntity = createCustomerEntity(customer);

        when(modelMapper.map(customer, CustomerEntity.class)).thenReturn(customerEntity);
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);
        when(repositoryJpa.createCustomer(customer)).thenReturn(customer);

        Customer customerSaved = repositoryJpa.createCustomer(customer);

        assertNotNull(customerSaved);
        assertEquals("Mateus Henrique", customerSaved.getCustomerName());
        verify(customerRepository, times(2)).save(customerEntity);
    }

    @Test
    @DisplayName("Should return list of customers")
    void listAllCustomersCase1(){
        Customer customer = new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17"));

        List<CustomerEntity> customerEntityList = Arrays.asList(
                new CustomerEntity("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")),
                new CustomerEntity("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")),
                new CustomerEntity("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17"))
        );

        List<Customer> customers = Arrays.asList(
                new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")),
                new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")),
                new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17"))
        );

        when(modelMapper.map(any(CustomerEntity.class), eq(Customer.class))).thenAnswer(invocation -> {
            CustomerEntity entity = invocation.getArgument(0);
            return new Customer(entity.getCustomerName(), entity.getCustomerCpf(), entity.getCustomerEmail(), entity.getCustomerBirthdate());
        });
        when(customerRepository.findAll()).thenReturn(customerEntityList);

        List<Customer> result = repositoryJpa.listAllCustomers();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should update a customer")
    void updateCustomerCase1(){
        String customerCpf = "111.123.456-10";
        String customerEmail = "mateusteste@gmail.com";
        String customerName = "Mateus Henrique Candido";

        Customer customer = new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17"));
        CustomerEntity entity = createCustomerEntity(customer);
        Customer customerUpdated = new Customer(customerName, customerCpf, customerEmail, LocalDate.parse("1999-10-17"));

        when(customerRepository.existsByCustomerCpf(customerCpf)).thenReturn(true);
        when(customerRepository.findByCustomerCpf(customerCpf)).thenReturn(entity);
        when(modelMapper.map(entity, Customer.class)).thenReturn(customerUpdated);
        when(repositoryJpa.updateCustomer(customerCpf, customerEmail, customerName)).thenReturn(customerUpdated);

        Customer result = repositoryJpa.updateCustomer(customerCpf, customerEmail, customerName);

        assertNotNull(result);
        assertTrue(customerName.equals(result.getCustomerName()));
        assertTrue(customerEmail.equals(result.getCustomerEmail()));
        verify(customerRepository, times(2)).existsByCustomerCpf(customerCpf);
        verify(customerRepository, times(2)).findByCustomerCpf(customerCpf);
    }

    @Test
    @DisplayName("Should not update a customer because not found the customer with the CPF entered")
    void updateCustomerCase2(){
        String customerCpf = "111.123.456-10";
        String customerEmail = "mateusteste@gmail.com";
        String customerName = "Mateus Henrique Candido";

        when(customerRepository.existsByCustomerCpf(customerCpf)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> repositoryJpa.updateCustomer(customerCpf, customerEmail, customerName));
    }
}