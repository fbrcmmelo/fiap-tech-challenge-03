package com.fiap.tech_challenge_03.domain.cadastro.entity;

import com.fiap.tech_challenge_03.application.cadastro.enums.StatusReserva;
import com.fiap.tech_challenge_03.infra.exception.DomainException;
import lombok.Getter;
import java.time.LocalDateTime;

import java.util.Objects;


@Getter

/*
 *  Entidade -> Núcleo do domínio
 *  Deve validar sua integridade ao ser criada
 *
 * */

public class Reserva {

    private String id;
    private Usuario usuario;
    private Restaurante restaurante;
    private LocalDateTime data;
    private Integer numeroPessoas;
    private StatusReserva status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Reserva(Usuario usuario, Restaurante restaurante, LocalDateTime data,
                   Integer numeroPessoas, StatusReserva status) {

        Objects.requireNonNull(usuario, "Usuário não pode ser nulo");
        Objects.requireNonNull(restaurante, "Restaurante não pode ser nulo");
        Objects.requireNonNull(data, "Data da reserva não pode ser nula");
        Objects.requireNonNull(numeroPessoas, "Número de pessoas não pode ser nulo");
        Objects.requireNonNull(status, "Status da reserva não pode ser nulo");

        if (numeroPessoas <= 0) {
            throw new DomainException("Número de pessoas na reserva deve ser maior que zero");
        }

        if (data.isBefore(LocalDateTime.now())) {
            throw new DomainException("Data da reserva não pode ser no passado");
        }

        this.usuario = usuario;
        this.restaurante = restaurante;
        this.data = data;
        this.numeroPessoas = numeroPessoas;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public void cancelarReserva() {
        if (status == StatusReserva.CANCELADA) {
            throw new DomainException("Reserva já está cancelada");
        }
        this.status = StatusReserva.CANCELADA;
        this.updatedAt = LocalDateTime.now();
    }

    public void confirmarReserva() {
        if (status == StatusReserva.CONFIRMADA) {
            throw new DomainException("Reserva já está confirmada");
        }
        this.status = StatusReserva.CONFIRMADA;
        this.updatedAt = LocalDateTime.now();
    }

    public void pendenteReserva() {
        if (status == StatusReserva.PENDENTE) {
            throw new DomainException("Reserva já está pendente");
        }
        this.status = StatusReserva.PENDENTE;
        this.updatedAt = LocalDateTime.now();
    }
}

