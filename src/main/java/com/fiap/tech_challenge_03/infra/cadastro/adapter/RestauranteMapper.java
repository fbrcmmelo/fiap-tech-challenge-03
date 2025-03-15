package com.fiap.tech_challenge_03.infra.cadastro.adapter;

import com.fiap.tech_challenge_03.application.cadastro.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.application.cadastro.dto.output.CadastrarRestauranteOutput;

public class RestauranteMapper {

    public static RestauranteDTO dtoFrom(CadastrarRestauranteOutput output) {
        return RestauranteDTO.builder()
                .id(output.id())
                .nome(output.nome())
                .build();

    }
}
