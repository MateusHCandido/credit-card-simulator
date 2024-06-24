package com.github.MateusHCandido.card_generating_service.application.gateway.exception;

public class CardAlreadyBlockedException extends Exception {
    public CardAlreadyBlockedException(String message) {
        super(message);
    }
}
