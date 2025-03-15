package com.fiap.tech_challenge_03.domain.cadastro.event;

import com.fiap.tech_challenge_03.infra.interfaces.IDomainEvent;
import lombok.Getter;

@Getter
public class EventoRestauranteCadastrado implements IDomainEvent {

    private String id;

    public EventoRestauranteCadastrado(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Restaurante Cadastrado";
    }
}
