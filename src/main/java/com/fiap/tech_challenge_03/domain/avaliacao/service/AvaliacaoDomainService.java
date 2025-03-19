package com.fiap.tech_challenge_03.domain.avaliacao.service;

import com.fiap.tech_challenge_03.domain.IDomainEventPubGateway;
import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.domain.avaliacao.event.EventoAvaliacaoRealizada;
import com.fiap.tech_challenge_03.domain.avaliacao.gateway.IAvaliacaoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliacaoDomainService {

    private final IAvaliacaoGateway gateway;
    private final IDomainEventPubGateway eventPub;

    public Avaliacao realizar(Avaliacao avaliacao) {
        final var avaliacaoCadastrada = this.gateway.cadastrar(avaliacao);
        this.eventPub.publish(new EventoAvaliacaoRealizada(avaliacaoCadastrada.getId(),
                avaliacaoCadastrada.getRestaurante().getId()));

        return avaliacaoCadastrada;
    }

}
