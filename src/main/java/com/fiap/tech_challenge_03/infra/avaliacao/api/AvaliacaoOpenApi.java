package com.fiap.tech_challenge_03.infra.avaliacao.api;

import com.fiap.tech_challenge_03.application.avaliacao.input.RealizarAvaliacaoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Avaliacoes")
public interface AvaliacaoOpenApi {

    @Operation(summary = "Realizar uma avaliacao")
    ResponseEntity<String> realizar(RealizarAvaliacaoInput request);
}
