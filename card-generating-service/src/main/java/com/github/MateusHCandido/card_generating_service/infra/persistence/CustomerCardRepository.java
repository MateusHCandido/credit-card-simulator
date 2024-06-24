package com.github.MateusHCandido.card_generating_service.infra.persistence;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerCardRepository extends JpaRepository<CustomerCardEntity, Long> {

    List<CustomerCardEntity> findByCustomerCpf(String customerCpf);
}
