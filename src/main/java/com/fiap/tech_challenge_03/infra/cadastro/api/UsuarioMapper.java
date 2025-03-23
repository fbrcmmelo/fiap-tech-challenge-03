package com.fiap.tech_challenge_03.infra.cadastro.api;

import com.fiap.tech_challenge_03.application.cadastro.output.UsuarioOutput;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.UsuarioDTO;

public class UsuarioMapper {

    public static UsuarioDTO dtoFrom(UsuarioOutput output) {
        return UsuarioDTO.builder()
                .id(output.id())
                .nome(output.nome())
                .email(output.email().getValue())
                .build();
    }
}
