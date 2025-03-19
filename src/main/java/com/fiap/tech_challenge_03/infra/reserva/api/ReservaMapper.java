package com.fiap.tech_challenge_03.infra.reserva.api;

import com.fiap.tech_challenge_03.application.cadastro.output.RestauranteOutput;
import com.fiap.tech_challenge_03.application.reserva.output.ReservaOutput;
import com.fiap.tech_challenge_03.infra.reserva.api.dto.ReservaDTO;

public class ReservaMapper {

    public static ReservaDTO dtoFrom(ReservaOutput output) {
        return ReservaDTO.builder()
                .id(output.id())
                .usuario(output.usuario())
                .numeroPessoas(output.numeroPessoas())
                .build();

    }
}
