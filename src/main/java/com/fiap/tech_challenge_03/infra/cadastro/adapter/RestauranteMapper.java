package com.fiap.tech_challenge_03.infra.cadastro.adapter;

import com.fiap.tech_challenge_03.application.cadastro.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.application.cadastro.dto.output.RestauranteOutput;

public class RestauranteMapper {

    public static RestauranteDTO dtoFrom(RestauranteOutput output) {
        return RestauranteDTO.builder()
                .id(output.id())
                .nome(output.nome())
                .build();

    }
}
