package com.fiap.tech_challenge_03.application.avaliacao.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record RealizarAvaliacaoInput(@Schema(example = "1") Integer nota,
                                     @Schema(example = "Comentário") String comentario,
                                     @Schema(description = "Usuário avaliador Id") String usuarioId,
                                     @Schema(description = "Restaurante avaliado Id") String restauranteId) {
}
