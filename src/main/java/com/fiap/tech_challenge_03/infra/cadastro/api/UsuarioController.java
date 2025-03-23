package com.fiap.tech_challenge_03.infra.cadastro.api;

import com.fiap.tech_challenge_03.application.cadastro.input.CadastrarUsuarioInput;
import com.fiap.tech_challenge_03.application.cadastro.usecase.ICadastrarUsuarioUseCase;
import com.fiap.tech_challenge_03.infra.adapter.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/usuarios", produces = "application/json")
public class UsuarioController implements UsuarioOpenApi {

    private final ICadastrarUsuarioUseCase cadastrarUseCase;

    @Override
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody CadastrarUsuarioInput request) {
        final var output = this.cadastrarUseCase.execute(request);
        final var dto = UsuarioMapper.dtoFrom(output);

        return ResponseEntity.status(201).body(Presenter.jsonFrom(dto));
    }
}
