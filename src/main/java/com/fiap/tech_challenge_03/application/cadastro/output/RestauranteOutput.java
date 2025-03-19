package com.fiap.tech_challenge_03.application.cadastro.output;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;

public record RestauranteOutput(String id,
                                String nome,
                                String tipoDeCozinha,
                                String cidade,
                                String estado,
                                Integer capacidade) {

    public static RestauranteOutput from(Restaurante restaurante) {
        return new RestauranteOutput(
                restaurante.getId(),
                restaurante.getNome().nome(),
                restaurante.getTipoDeCozinha(),
                restaurante.getLocalidade().getCidade(),
                restaurante.getLocalidade().getEstado(),
                restaurante.getCapacidade());
    }
}
