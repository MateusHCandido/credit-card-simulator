package com.github.MateusHCandido.card_generating_service.application.gateway.exception;

public class CardAlreadyCanceledException extends Exception {
    public CardAlreadyCanceledException(String message) {
        super(message);
    }
}
