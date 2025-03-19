package com.fiap.tech_challenge_03.infra.cadastro.api;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.input.CadastrarRestauranteInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Cadastros")
public interface RestauranteOpenApi {

    @Operation(summary = "Cadastrar um restaurante", responses = @ApiResponse())
    ResponseEntity<String> cadastrar(CadastrarRestauranteInput request);

    @Operation(summary = "Buscar restaurantes", responses = @ApiResponse())
    ResponseEntity<String> buscarComParametros(BuscarRestauranteInput request);
}
