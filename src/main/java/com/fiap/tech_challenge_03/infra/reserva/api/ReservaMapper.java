package com.fiap.tech_challenge_03.infra.reserva.api;

import com.fiap.tech_challenge_03.application.reserva.output.ReservaOutput;
import com.fiap.tech_challenge_03.infra.reserva.api.dto.ReservaDTO;

import java.time.format.DateTimeFormatter;

public class ReservaMapper {

    public static ReservaDTO dtoFrom(ReservaOutput output) {
        return ReservaDTO.builder()
                .id(output.id())
                .restauranteId(output.restauranteId())
                .usuarioId(output.usuarioId())
                .data(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(output.data()))
                .numeroPessoas(output.numeroPessoas())
                .status(output.status().name())
                .build();
    }
}

