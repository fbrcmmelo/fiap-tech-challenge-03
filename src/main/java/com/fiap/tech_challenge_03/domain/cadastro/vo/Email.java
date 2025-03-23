package com.fiap.tech_challenge_03.domain.cadastro.vo;

import lombok.Getter;

@Getter
public class Email {

    @jakarta.validation.constraints.Email
    private final String value;

    public Email(String value) {
        this.value = value;
    }
}
