package com.github.MateusHCandido.card_generating_service.infra.persistence;

import com.github.MateusHCandido.card_generating_service.domain.entities.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerCardId;
    private String customerCpf;
    @ManyToOne(fetch = FetchType.LAZY)
    private CardEntity card;
    private BigDecimal customerCardLimit;
}
