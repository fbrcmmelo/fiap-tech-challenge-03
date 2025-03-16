package com.fiap.tech_challenge_03.domain.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.dto.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.dto.output.RestauranteOutput;
import com.fiap.tech_challenge_03.application.cadastro.usecase.IBuscarRestaurantesUseCase;
import com.fiap.tech_challenge_03.domain.cadastro.service.RestauranteDomainService;
import com.fiap.tech_challenge_03.infra.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class BuscarRestaurantesUseCase implements IBuscarRestaurantesUseCase {

    private final RestauranteDomainService service;

    @Override
    public Collection<RestauranteOutput> execute(BuscarRestauranteInput input) {
        if (input == null) {
            throw new DomainException("Busca precisa ter ao menos um parâmetro");
        }
        return this.service.buscaComParametros(input);
    }
}
