package com.fiap.tech_challenge_03.application.cadastro.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

public record CadastrarReservaInput(
        @Schema(example = "1", description = "ID do usuário") Long usuarioId,
        @Schema(example = "10", description = "ID do restaurante") Long restauranteId,
        @Schema(example = "2025-05-01T19:00:00", description = "Data e hora da reserva") String data,
        @Schema(example = "4", description = "Número de pessoas na reserva") Integer numeroPessoas) {
}
