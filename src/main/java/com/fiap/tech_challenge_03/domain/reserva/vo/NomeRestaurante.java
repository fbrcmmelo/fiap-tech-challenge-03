package com.fiap.tech_challenge_03.domain.reserva.vo;

import com.fiap.tech_challenge_03.domain.DomainException;

import java.util.Objects;

/*
 *  Value Object -> Imutável, criação de um dado consistente e integro
 *  Ajuda na construção de entidades consistentes.
 *  Deve validar sua integridade ao ser criada
 *
 * */

public record NomeRestaurante(String nome) {

    public NomeRestaurante {
        Objects.requireNonNull(nome, "Nome do restaurante não pode ser nulo");

        if (nome.isBlank()) {
            throw new DomainException("Nome do restaurante não pode estar vazio");
        }
    }
}
