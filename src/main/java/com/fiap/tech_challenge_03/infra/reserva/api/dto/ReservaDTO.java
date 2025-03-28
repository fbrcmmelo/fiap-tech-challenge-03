package com.fiap.tech_challenge_03.infra.reserva.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservaDTO {

    private String id;
    private String restauranteId;
    private String usuarioId;
    private String data;
    private int numeroPessoas;
    private String status;
}

