package com.fiap.tech_challenge_03.infra.avaliacao.api;

import com.fiap.tech_challenge_03.application.avaliacao.output.AvaliacaoOutput;
import com.fiap.tech_challenge_03.infra.avaliacao.api.dto.AvaliacaoDTO;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.UsuarioDTO;

public class AvaliacaoMapper {

    public static AvaliacaoDTO dtoFrom(AvaliacaoOutput output) {
        return AvaliacaoDTO.builder()
                .id(output.id())
                .nota(output.nota())
                .comentario(output.comentario())
                .avaliadorDto(UsuarioDTO.builder()
                        .nome(output.avaliador().getNome())
                        .build())
                .restauranteDto(RestauranteDTO.builder()
                        .nome(output.restaurante().getNome().nome())
                        .build())
                .build();

    }
}
