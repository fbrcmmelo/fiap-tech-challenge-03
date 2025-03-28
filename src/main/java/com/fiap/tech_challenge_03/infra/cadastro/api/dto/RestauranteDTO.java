package com.fiap.tech_challenge_03.infra.cadastro.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Funcionamento;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Localidade;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Mesa;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class RestauranteDTO {

    private String id;
    private String nome;
    private String tipoDeCozinha;
    private Localidade localidade;
    private Funcionamento funcionamento;
    private List<Mesa> mesas;

}
