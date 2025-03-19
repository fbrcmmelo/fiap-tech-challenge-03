package com.fiap.tech_challenge_03.domain.avaliacao.gateway;

import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;

public interface IAvaliacaoGateway {
    Avaliacao cadastrar(Avaliacao avaliacao);
}
