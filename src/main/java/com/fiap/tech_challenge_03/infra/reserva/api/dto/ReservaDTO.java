package com.fiap.tech_challenge_03.infra.reserva.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.UsuarioDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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

