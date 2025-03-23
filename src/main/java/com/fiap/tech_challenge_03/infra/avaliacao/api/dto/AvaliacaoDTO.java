package com.fiap.tech_challenge_03.infra.avaliacao.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.UsuarioDTO;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class AvaliacaoDTO {

    private String id;
    private Integer nota;
    private String comentario;
    private UsuarioDTO avaliadorDto;
    private RestauranteDTO restauranteDto;

}
