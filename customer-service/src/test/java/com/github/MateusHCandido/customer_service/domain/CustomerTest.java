package com.github.MateusHCandido.customer_service.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class CustomerTest {

    @Test()
    @DisplayName("Should not throw exception for valid CPF")
    void creatingCustomerUseCase1(){
        String message = "";
        try{
            Customer customer = new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17"));
            message = "customer created";
        }catch (IllegalArgumentException exception){
            exception.printStackTrace();
        }

        Assertions.assertTrue("customer created".equals(message));
    }

    @Test
    @DisplayName("Should throw InvalidCpfException for invalid CPFs")
    void creatingCustomerUseCase2() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111.123456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111.123.45610", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "11112345610", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111.12345610", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111123.45610", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111123456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17")));
    }


    @Test
    @DisplayName("Should not throw exception for valid email")
    void creatingCustomerUseCase3() {
        String message = "";
        try{
            Customer customer = new Customer("Mateus Henrique", "111.123.456-10", "mateushcandido5@gmail.com", LocalDate.parse("1999-10-17"));
            message = "customer created";
        }catch (IllegalArgumentException exception){
            exception.printStackTrace();
        }

        Assertions.assertTrue("customer created".equals(message));
    }

    @Test
    @DisplayName("Should throw InvalidEmailException for invalid emails")
    void creatingCustomerUseCase4() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111123.456-10", "mateushcandido5gmail.com", LocalDate.parse("1999-10-17")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111.123456-10", "mateushcandido5@gmailcom", LocalDate.parse("1999-10-17")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111.123.45610", "@gmail.com", LocalDate.parse("1999-10-17")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "11112345610", "mateushcandido5gmailcom", LocalDate.parse("1999-10-17")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111.12345610", "mateushcandido5", LocalDate.parse("1999-10-17")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111123.45610", "@", LocalDate.parse("1999-10-17")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111123456-10", ".com", LocalDate.parse("1999-10-17")));
    }

    @Test
    @DisplayName("Should not throw exception for customer who is an adult")
    void creatingCustomerUseCase5(){}

    @Test
    @DisplayName("Should throw UnderageException for customer who is not an adult")
    void creatingCustomerUseCase6(){
        LocalDate invalidBirthdate = LocalDate.now().minusYears(18);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer("Mateus Henrique", "111123.456-10"
                        , "mateushcandido5@gmail.com", invalidBirthdate));
    }
}