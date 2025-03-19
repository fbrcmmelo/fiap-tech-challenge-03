package com.fiap.tech_challenge_03.domain.avaliacao.event;

import com.fiap.tech_challenge_03.domain.IDomainEvent;
import lombok.Getter;

@Getter
public class EventoAvaliacaoRealizada implements IDomainEvent {

    private String avaliacaoId;
    private String restauranteId;

    public EventoAvaliacaoRealizada(String avaliacaoId, String restauranteId) {
        this.avaliacaoId = avaliacaoId;
        this.restauranteId = restauranteId;
    }

    @Override
    public String toString() {
        return "Avaliacao Realizada";
    }
}
