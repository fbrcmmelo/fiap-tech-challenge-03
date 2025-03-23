package com.fiap.tech_challenge_03.infra.cadastro.api;

import com.fiap.tech_challenge_03.application.cadastro.input.CadastrarUsuarioInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Cadastros")
public interface UsuarioOpenApi {

    @Operation(summary = "Cadastrar um usuario", responses = @ApiResponse())
    ResponseEntity<String> cadastrar(CadastrarUsuarioInput request);
}
