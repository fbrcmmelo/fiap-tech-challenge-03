package com.fiap.tech_challenge_03.domain.cadastro.usecase;

import com.fiap.tech_challenge_03.application.IUseCaseIO;
import com.fiap.tech_challenge_03.application.cadastro.output.RestauranteOutput;
import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IRestauranteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BuscarRestaurantePorIdUseCase implements
        IUseCaseIO<String, RestauranteOutput> {

    private final IRestauranteGateway gateway;

    @Override
    public RestauranteOutput execute(String id) {
        var restaurante = this.gateway.buscarPorId(id);

        if (restaurante.isEmpty()) {
            throw new DomainException("Restaurante de id:".concat(id).concat(" não econtrado"));
        }

        return RestauranteOutput.from(restaurante.get());
    }
}
