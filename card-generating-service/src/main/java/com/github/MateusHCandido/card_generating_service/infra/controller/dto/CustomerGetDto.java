package com.github.MateusHCandido.card_generating_service.infra.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGetDto {

    private String customerCpf;
}
