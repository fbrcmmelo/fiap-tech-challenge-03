package com.fiap.tech_challenge_03.infra.avaliacao.gateway;

import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.domain.avaliacao.gateway.IAvaliacaoGateway;
import com.fiap.tech_challenge_03.infra.avaliacao.entity.AvaliacaoEntity;
import com.fiap.tech_challenge_03.infra.avaliacao.repository.AvaliacaoMongoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AvaliacaoGatewayImpl implements IAvaliacaoGateway {

    private final AvaliacaoMongoRepository repository;

    @Override
    public Avaliacao cadastrar(Avaliacao avaliacao) {
        final var entityMongoSaved = this.repository.save(new AvaliacaoEntity(avaliacao));
        return entityMongoSaved.toAvaliacao();
    }
}
