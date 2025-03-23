package com.fiap.tech_challenge_03.utils;

import com.fiap.tech_challenge_03.application.avaliacao.input.RealizarAvaliacaoInput;
import com.fiap.tech_challenge_03.application.avaliacao.output.AvaliacaoOutput;
import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;

public class AvaliacaoBuilder {

    AvaliacaoBuilder() {
        throw new IllegalStateException("Classe Utilitaria");
    }

    public static Avaliacao entity() {
        return new Avaliacao("2", 5, "comentario", UsuarioBuilder.entity(), RestauranteBuilder.entity());
    }

    public static RealizarAvaliacaoInput realizarAvaliacaoInput() {
        return new RealizarAvaliacaoInput(1, "1", " ", "1");
    }

    public static AvaliacaoOutput output() {
        return new AvaliacaoOutput("id", 10, "comentario", UsuarioBuilder.entity(), RestauranteBuilder.entity());
    }
}
