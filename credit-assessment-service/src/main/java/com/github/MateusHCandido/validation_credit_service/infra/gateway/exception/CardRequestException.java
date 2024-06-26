package com.github.MateusHCandido.validation_credit_service.infra.gateway.exception;

public class CardRequestException extends RuntimeException {
    public CardRequestException(String message){
        super(message);
    }
}
