package com.fiap.tech_challenge_03.application.reserva.output;

import com.fiap.tech_challenge_03.application.reserva.enums.StatusReserva;
import com.fiap.tech_challenge_03.domain.reserva.entity.Reserva;

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
                Long.parseLong(reserva.getUsuarioId()),
                Long.parseLong(reserva.getRestauranteId()),
                reserva.getData(),
                reserva.getNumeroPessoas(),
                reserva.getStatus()
        );
    }
}
