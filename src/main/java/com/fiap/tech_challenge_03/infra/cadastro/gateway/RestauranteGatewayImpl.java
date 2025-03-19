package com.fiap.tech_challenge_03.infra.cadastro.gateway;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IRestauranteGateway;
import com.fiap.tech_challenge_03.infra.cadastro.entity.RestauranteJpaEntity;
import com.fiap.tech_challenge_03.infra.cadastro.repository.RestauranteMongoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RestauranteGatewayImpl implements IRestauranteGateway {

    private final RestauranteMongoRepository repository;

    @Override
    public Restaurante cadastrar(Restaurante restaurante) {
        //TODO : ajustar com mapeamento das collections ja criadas pelo Gabriel
        // final var entity = this.repository.save(new RestauranteEntity(restaurante));
        // return entity.toRestaurante();
        return new RestauranteJpaEntity(restaurante).toRestaurante();
    }

    @Override
    public List<Restaurante> buscarComParametros(BuscarRestauranteInput input) {
        final var example = new RestauranteJpaEntity(input).toExample();
//        return this.repository.findAll(example).stream().map(RestauranteEntity::toRestaurante).toList();

        return Arrays.asList();
    }

    @Override
    public Optional<Restaurante> buscarPorId(String id) {
        final var byId = this.repository.findById(id);
        if (byId.isEmpty()) {
            return Optional.empty();
        }

        return byId.map(RestauranteJpaEntity::toRestaurante);
    }
}
