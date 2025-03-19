package com.fiap.tech_challenge_03.infra.avaliacao.event;

import com.fiap.tech_challenge_03.domain.avaliacao.event.EventoAvaliacaoRealizada;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AvaliacaoEventListener {

    @EventListener
    public void avaliacaoRealizada(final EventoAvaliacaoRealizada evento) {
        log.info("Avaliacao de id : {} para restaurante de id : {}, realizada com sucesso.",
                evento.getAvaliacaoId(), evento.getRestauranteId());

        log.info("Eviando e-mail para o administrativo ...");
    }
}
