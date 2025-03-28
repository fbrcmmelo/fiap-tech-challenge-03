package com.fiap.tech_challenge_03.infra.reserva.api;

import com.fiap.tech_challenge_03.application.reserva.input.CadastrarReservaInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Reservas")
public interface ReservaOpenApi {

    @Operation(summary = "Cadastrar uma resera", responses = @ApiResponse())
    ResponseEntity<String> cadastrar(CadastrarReservaInput request);

}
