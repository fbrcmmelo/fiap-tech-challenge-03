package com.fiap.tech_challenge_03.infra.avaliacao.api;

import com.fiap.tech_challenge_03.application.avaliacao.output.AvaliacaoOutput;
import com.fiap.tech_challenge_03.infra.avaliacao.api.dto.AvaliacaoDTO;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;

public class AvaliacaoMapper {

    AvaliacaoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static AvaliacaoDTO dtoFrom(AvaliacaoOutput output) {
        return AvaliacaoDTO.builder()
                .id(output.id())
                .nota(output.nota())
                .comentario(output.comentario())
                .avaliadorDto(output.avaliador())
                .restauranteDto(RestauranteDTO.builder()
                        .id(output.restaurante().getId())
                        .nome(output.restaurante().getNome().nome()).build())
                .build();

    }
}
