package com.github.MateusHCandido.customer_service.infra.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String customerName;
    @Column(unique = true)
    private String customerCpf;
    private String customerEmail;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate customerBirthdate;

    @Column(updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Fortaleza")
    private LocalDateTime customerCreatedAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Fortaleza")
    private LocalDateTime customerLastUpdatedAt;

    public CustomerEntity(String customerName, String customerCpf, String customerEmail, LocalDate customerBirthdate) {
        this.customerName = customerName;
        this.customerCpf = customerCpf;
        this.customerEmail = customerEmail;
        this.customerBirthdate = customerBirthdate;
    }


    @PrePersist
    protected void onCreate(){
        this.customerCreatedAt = LocalDateTime.now();
    }
}
