package com.fiap.tech_challenge_03.application.reserva.output;

import com.fiap.tech_challenge_03.application.reserva.enums.StatusReserva;
import com.fiap.tech_challenge_03.domain.reserva.entity.Reserva;

import java.time.LocalDateTime;

public record ReservaOutput(
        String id,
        String usuarioId,
        String restauranteId,
        LocalDateTime data,
        Integer numeroPessoas,
        StatusReserva status) {

    public static ReservaOutput from(Reserva reserva) {
        return new ReservaOutput(
                reserva.getId(),
                reserva.getUsuarioId(),
                reserva.getRestauranteId(),
                reserva.getData(),
                reserva.getNumeroPessoas(),
                reserva.getStatus()
        );
    }
}
