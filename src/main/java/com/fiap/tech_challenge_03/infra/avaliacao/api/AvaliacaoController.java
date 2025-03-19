package com.fiap.tech_challenge_03.infra.avaliacao.api;

import com.fiap.tech_challenge_03.application.avaliacao.input.RealizarAvaliacaoInput;
import com.fiap.tech_challenge_03.domain.avaliacao.usecase.RealizarAvaliacaoUseCase;
import com.fiap.tech_challenge_03.infra.adapter.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/avaliacoes", produces = "application/json")
public class AvaliacaoController implements AvaliacaoOpenApi {

    private final RealizarAvaliacaoUseCase useCase;

    @Override
    @PostMapping
    public ResponseEntity<String> realizar(RealizarAvaliacaoInput input) {
        final var output = this.useCase.execute(input);
        final var dto = AvaliacaoMapper.dtoFrom(output);

        return ResponseEntity.status(201).body(Presenter.jsonFrom(dto));
    }
}
