package com.fiap.tech_challenge_03.application.avaliacao.output;

import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;

public record AvaliacaoOutput(String id,
                              Integer nota,
                              String comentario,
                              Object avaliador,
                              Restaurante restaurante) {

    public static AvaliacaoOutput from(Avaliacao avaliacao) {
        return new AvaliacaoOutput(
                avaliacao.getId(),
                avaliacao.getNota().getValorNota(),
                avaliacao.getComentario(),
                avaliacao.getAvaliador(),
                avaliacao.getRestaurante());
    }
}
