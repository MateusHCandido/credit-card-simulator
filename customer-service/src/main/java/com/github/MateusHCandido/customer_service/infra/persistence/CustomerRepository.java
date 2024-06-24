package com.github.MateusHCandido.customer_service.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByCustomerCpf(String customerCpf);

    boolean existsByCustomerCpf(String customerCpf);
}
