package com.fiap.tech_challenge_03.infra.cadastro.gateway;

import com.fiap.tech_challenge_03.application.cadastro.dto.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IRestauranteGateway;
import com.fiap.tech_challenge_03.infra.cadastro.entity.RestauranteEntity;
import com.fiap.tech_challenge_03.infra.cadastro.repository.RestauranteMongoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class RestauranteGatewayImpl implements IRestauranteGateway {

    private final RestauranteMongoRepository repository;

    @Override
    public Restaurante cadastrar(Restaurante restaurante) {
        //TODO : ajustar com mapeamento das collections ja criadas pelo Gabriel
        // final var entity = this.repository.save(new RestauranteEntity(restaurante));
        // return entity.toRestaurante();
        return new RestauranteEntity(restaurante).toRestaurante();
    }

    @Override
    public List<Restaurante> buscarComParametros(BuscarRestauranteInput input) {
//        final var example = new RestauranteEntity(input).toExample();
//        return this.repository.findAll(example).stream().toList();

        return Arrays.asList();
    }
}
