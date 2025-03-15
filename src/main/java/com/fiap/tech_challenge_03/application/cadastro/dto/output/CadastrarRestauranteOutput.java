package com.fiap.tech_challenge_03.application.cadastro.dto.output;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;

public record CadastrarRestauranteOutput(String id,
                                         String nome,
                                         String tipoDeCozinha,
                                         String cidade,
                                         String estado,
                                         Integer capacidade) {

    public static CadastrarRestauranteOutput from(Restaurante restaurante) {
        return new CadastrarRestauranteOutput(
                restaurante.getId(),
                restaurante.getNome().nome(),
                restaurante.getTipoDeCozinha(),
                restaurante.getLocalidade().getCidade(),
                restaurante.getLocalidade().getEstado(),
                restaurante.getCapacidade());
    }
}
