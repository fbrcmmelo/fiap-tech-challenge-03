package com.fiap.tech_challenge_03.domain.reserva.entity;

import com.fiap.tech_challenge_03.application.reserva.enums.StatusReserva;
import com.fiap.tech_challenge_03.domain.DomainException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

/*
 *  Entidade -> Núcleo do domínio
 *  Deve validar sua integridade ao ser criada
 *
 * */

@Getter
public class Reserva {

    private final String usuarioId; // Agora recebe apenas o ID do usuário
    private final String restauranteId; // Agora recebe apenas o ID do restaurante
    private final LocalDateTime data;
    private final Integer numeroPessoas;
    private final LocalDateTime createdAt;
    private String id;
    private StatusReserva status;
    private LocalDateTime updatedAt;

    public Reserva(String id, String usuarioId, String restauranteId, LocalDateTime data,
                   Integer numeroPessoas, StatusReserva status) {

        Objects.requireNonNull(usuarioId, "Usuário ID não pode ser nulo");
        Objects.requireNonNull(restauranteId, "Restaurante ID não pode ser nulo");
        Objects.requireNonNull(data, "Data da reserva não pode ser nula");
        Objects.requireNonNull(numeroPessoas, "Número de pessoas não pode ser nulo");
        Objects.requireNonNull(status, "Status da reserva não pode ser nulo");

        if (numeroPessoas <= 0) {
            throw new DomainException("Número de pessoas na reserva deve ser maior que zero");
        }

        if (data.isBefore(LocalDateTime.now())) {
            throw new DomainException("Data da reserva não pode ser no passado");
        }

        this.id = id;
        this.usuarioId = usuarioId;
        this.restauranteId = restauranteId;
        this.data = data;
        this.numeroPessoas = numeroPessoas;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }
}

