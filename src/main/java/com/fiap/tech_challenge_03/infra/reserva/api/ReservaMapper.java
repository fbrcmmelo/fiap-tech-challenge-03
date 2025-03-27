package com.fiap.tech_challenge_03.infra.reserva.api;

import com.fiap.tech_challenge_03.application.cadastro.output.RestauranteOutput;
import com.fiap.tech_challenge_03.application.reserva.output.ReservaOutput;
import com.fiap.tech_challenge_03.infra.reserva.api.dto.ReservaDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ReservaMapper {

    public static ReservaDTO dtoFrom(ReservaOutput output) {

        String status = output.status() != null ? output.status().toString() : "PENDENTE"; // Substitua "DEFAULT_STATUS" por um valor apropriado.


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

