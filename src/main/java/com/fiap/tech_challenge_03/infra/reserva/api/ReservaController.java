package com.fiap.tech_challenge_03.infra.reserva.api;

import com.fiap.tech_challenge_03.application.reserva.input.CadastrarReservaInput;
import com.fiap.tech_challenge_03.domain.reserva.usecase.CadastrarReservaUseCase;
import com.fiap.tech_challenge_03.infra.adapter.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/reservas", produces = "application/json")
public class ReservaController implements ReservaOpenApi {

    private final CadastrarReservaUseCase useCase;

    @Override
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody CadastrarReservaInput input) {
        final var output = this.useCase.execute(input);
        final var dto = ReservaMapper.dtoFrom(output);

        return ResponseEntity.status(201).body(Presenter.jsonFrom(dto));
    }
}
