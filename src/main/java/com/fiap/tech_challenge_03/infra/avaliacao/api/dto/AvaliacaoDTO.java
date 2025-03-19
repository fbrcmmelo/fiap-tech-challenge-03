package com.fiap.tech_challenge_03.infra.avaliacao.api.dto;

import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvaliacaoDTO {

    private String id;
    private Integer nota;
    private String comentario;
    private Object avaliadorDto;
    private RestauranteDTO restauranteDto;

}
