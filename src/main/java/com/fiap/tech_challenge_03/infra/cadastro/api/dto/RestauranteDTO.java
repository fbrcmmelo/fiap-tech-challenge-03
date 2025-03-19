package com.fiap.tech_challenge_03.infra.cadastro.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestauranteDTO {

    private String id;
    private String nome;
}
