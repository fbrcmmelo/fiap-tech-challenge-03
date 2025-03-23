package com.fiap.tech_challenge_03.infra.cadastro.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class UsuarioDTO {

    private String id;
    private String nome;
    private String email;
}
