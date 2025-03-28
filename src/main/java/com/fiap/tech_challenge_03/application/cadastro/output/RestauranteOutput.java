package com.fiap.tech_challenge_03.application.cadastro.output;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Funcionamento;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Localidade;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Mesa;

import java.util.List;

public record RestauranteOutput(String id,
                                String nome,
                                String tipoDeCozinha,
                                Localidade localidade,
                                Funcionamento funcionamento,
                                Integer quantidadeMesas,
                                List<Mesa> mesas) {

    public static RestauranteOutput from(Restaurante restaurante) {
        return new RestauranteOutput(
                restaurante.getId(),
                restaurante.getNome().nome(),
                restaurante.getTipoDeCozinha(),
                restaurante.getLocalidade(),
                restaurante.getFuncionamento(),
                restaurante.getQuantidadeMesas(),
                restaurante.getMesas());
    }
}
