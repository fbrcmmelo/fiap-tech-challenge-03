package com.fiap.tech_challenge_03.domain.reserva.event;

import com.fiap.tech_challenge_03.domain.IDomainEvent;
import lombok.Getter;

@Getter
public class EventoReservaCadastrada implements IDomainEvent {

    private final String id;

    public EventoReservaCadastrada(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reservar Cadastrado";
    }
}
