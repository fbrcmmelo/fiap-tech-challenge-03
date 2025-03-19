package com.fiap.tech_challenge_03.utils;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.input.CadastrarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.output.RestauranteOutput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Fucionamento;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Localidade;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.infra.cadastro.entity.RestauranteJpaEntity;

import java.util.Set;

public class RestauranteBuilder {

    RestauranteBuilder() {
        throw new IllegalStateException("Classe Utilitaria");
    }

    public static Restaurante entity() {
        return new Restaurante(
                "Restaurante Teste",
                new Localidade(123, "logradouro", "Cidade Teste", "Estado Teste"),
                new Fucionamento("08:00", "22:00", Set.of(1, 2)),
                100,
                "Italiana"
        );
    }

    public static CadastrarRestauranteInput cadastroInput() {
        return new CadastrarRestauranteInput("nome", "cidade", "estado", 12312,
                "logradouro", "tipo de cozinha", "07:00", "13:00", Set.of(1, 2), 10);
    }

    public static RestauranteOutput output() {
        return new RestauranteOutput("id", "nome", "tipo de cozinha", "cidade", "estado", 123);
    }

    public static RestauranteDTO dto() {
        return RestauranteDTO.builder()
                .id("teste")
                .nome("teste")
                .build();
    }

    public static RestauranteJpaEntity jpaEntity() {
        return new RestauranteJpaEntity(entity());
    }

    public static BuscarRestauranteInput buscarComParametrosInput() {
        return new BuscarRestauranteInput("nome", "cidade", "estado", "logradouro", "tipo de cozinha");
    }
}
