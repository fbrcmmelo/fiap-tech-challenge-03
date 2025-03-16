package com.fiap.tech_challenge_03.utils;

import com.fiap.tech_challenge_03.application.cadastro.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.application.cadastro.dto.input.CadastrarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.dto.output.CadastrarRestauranteOutput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Fucionamento;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Localidade;
import com.fiap.tech_challenge_03.infra.cadastro.entity.RestauranteEntity;

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

    public static CadastrarRestauranteOutput cadastroOutput() {
        return new CadastrarRestauranteOutput("id", "nome", "tipo de cozinha", "cidade", "estado", 123);
    }

    public static RestauranteDTO dto() {
        return RestauranteDTO.builder()
                .id("teste")
                .nome("teste")
                .build();
    }

    public static RestauranteEntity jpaEntity() {
        return new RestauranteEntity(entity());
    }
}
