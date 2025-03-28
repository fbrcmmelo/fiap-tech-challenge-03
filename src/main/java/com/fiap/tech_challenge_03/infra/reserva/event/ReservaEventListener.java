package com.fiap.tech_challenge_03.infra.reserva.event;

import com.fiap.tech_challenge_03.domain.reserva.event.EventoReservaCadastrada;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReservaEventListener {

    @EventListener
    public void reservaCadastrado(final EventoReservaCadastrada evento) {
        log.info("Reserva cadastrado com sucesso, id: {}", evento.getId());
    }
}
