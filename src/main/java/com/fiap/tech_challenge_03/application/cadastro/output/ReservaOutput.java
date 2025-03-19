package com.fiap.tech_challenge_03.application.cadastro.dto.output;

import com.fiap.tech_challenge_03.application.cadastro.enums.StatusReserva;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Reserva;

import java.time.LocalDateTime;

public record ReservaOutput(
        String id,
        Long usuarioId,
        Long restauranteId,
        LocalDateTime data,
        Integer numeroPessoas,
        StatusReserva status) {

    public static ReservaOutput from(Reserva reserva) {
        return new ReservaOutput(
                reserva.getId(),
                reserva.getUsuario().getId(),
                reserva.getRestaurante().getId(),
                reserva.getData(),
                reserva.getNumeroPessoas(),
                reserva.getStatus()
        );
    }
}
