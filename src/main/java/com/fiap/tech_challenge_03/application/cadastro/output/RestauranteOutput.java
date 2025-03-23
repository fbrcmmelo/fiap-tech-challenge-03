package com.fiap.tech_challenge_03.application.cadastro.output;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Funcionamento;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Localidade;

public record RestauranteOutput(String id,
                                String nome,
                                String tipoDeCozinha,
                                Localidade localidade,
                                Funcionamento funcionamento,
                                Integer capacidade) {

    public static RestauranteOutput from(Restaurante restaurante) {
        return new RestauranteOutput(
                restaurante.getId(),
                restaurante.getNome().nome(),
                restaurante.getTipoDeCozinha(),
                restaurante.getLocalidade(),
                restaurante.getFuncionamento(),
                restaurante.getCapacidade());
    }
}
