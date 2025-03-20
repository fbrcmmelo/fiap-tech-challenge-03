package com.fiap.tech_challenge_03.infra.reserva.api.dto;

import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.UsuarioDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservaDTO {

    private String id;
    private Long restauranteId; // Alterado para armazenar apenas o ID
    private Long usuarioId; // Alterado para armazenar apenas o ID
    private LocalDateTime data;
    private int numeroPessoas;
    private String status;
}

