package com.fiap.tech_challenge_03.infra.reserva.api;

import com.fiap.tech_challenge_03.application.cadastro.output.RestauranteOutput;
import com.fiap.tech_challenge_03.application.reserva.output.ReservaOutput;
import com.fiap.tech_challenge_03.infra.reserva.api.dto.ReservaDTO;

public class ReservaMapper {

    public static ReservaDTO dtoFrom(ReservaOutput output) {
        return ReservaDTO.builder()
                .id(output.id())
                .restauranteId(output.restauranteId()) // Apenas ID
                .usuarioId(output.usuarioId()) // Apenas ID
                .data(output.data())
                .numeroPessoas(output.numeroPessoas())
                .status(output.status().toString())
                .build();
    }
}

