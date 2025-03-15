package com.fiap.tech_challenge_03.infra.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
