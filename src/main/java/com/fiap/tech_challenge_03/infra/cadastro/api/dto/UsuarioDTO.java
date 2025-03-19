package com.fiap.tech_challenge_03.infra.cadastro.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {
    private String id;
    private String nome;
    private String email;
}
