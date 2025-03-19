package com.fiap.tech_challenge_03.infra.cadastro.api;

import com.fiap.tech_challenge_03.application.cadastro.output.RestauranteOutput;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;

public class RestauranteMapper {

    public static RestauranteDTO dtoFrom(RestauranteOutput output) {
        return RestauranteDTO.builder()
                .id(output.id())
                .nome(output.nome())
                .build();

    }
}
