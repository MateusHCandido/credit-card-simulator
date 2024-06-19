package com.github.MateusHCandido.card_generating_service.application.gateway.exception;

public class CardAlreadyActiveException extends Exception {
    public CardAlreadyActiveException(String message) {
        super(message);
    }
}
