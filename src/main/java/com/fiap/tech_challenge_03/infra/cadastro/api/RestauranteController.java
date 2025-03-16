package com.fiap.tech_challenge_03.infra.cadastro.api;

import com.fiap.tech_challenge_03.application.cadastro.dto.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.dto.input.CadastrarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.usecase.IBuscarRestaurantesUseCase;
import com.fiap.tech_challenge_03.application.cadastro.usecase.ICadastrarRestauranteUseCase;
import com.fiap.tech_challenge_03.infra.adapter.Presenter;
import com.fiap.tech_challenge_03.infra.cadastro.adapter.RestauranteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/restaurantes", produces = "application/json")
public class RestauranteController implements RestauranteOpenApi {

    private final ICadastrarRestauranteUseCase cadastrarUseCase;
    private final IBuscarRestaurantesUseCase buscarRestaurantesUseCase;

    @Override
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody CadastrarRestauranteInput request) {
        final var output = this.cadastrarUseCase.execute(request);
        final var dto = RestauranteMapper.dtoFrom(output);

        return ResponseEntity.status(201).body(Presenter.jsonFrom(dto));
    }

    @Override
    @PostMapping("/buscar")
    public ResponseEntity<String> buscarComParametros(@RequestBody BuscarRestauranteInput request) {
        final var outputs = this.buscarRestaurantesUseCase.execute(request);
        final var dtos = outputs.stream().map(RestauranteMapper::dtoFrom).toList();

        return ResponseEntity.status(200).body(Presenter.jsonFrom(dtos));
    }
}
