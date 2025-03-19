package com.fiap.tech_challenge_03.domain.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.output.RestauranteOutput;
import com.fiap.tech_challenge_03.application.cadastro.usecase.IBuscarRestaurantesComParametrosUseCase;
import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.domain.cadastro.service.RestauranteDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class BuscarRestaurantesComParametrosUseCase implements IBuscarRestaurantesComParametrosUseCase {

    private final RestauranteDomainService service;

    @Override
    public Collection<RestauranteOutput> execute(BuscarRestauranteInput input) {
        if (input == null) {
            throw new DomainException("Busca precisa ter ao menos um parâmetro");
        }
        return this.service.buscarComParametros(input)
                .stream()
                .map(RestauranteOutput::from)
                .toList();
    }
}
