package com.fiap.tech_challenge_03.infra.cadastro.gateway;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IRestauranteGateway;
import com.fiap.tech_challenge_03.infra.cadastro.entity.RestauranteJpaEntity;
import com.fiap.tech_challenge_03.infra.cadastro.repository.RestauranteMongoRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RestauranteGatewayImpl implements IRestauranteGateway {

    private final RestauranteMongoRepository repository;

    @Override
    public Restaurante cadastrar(Restaurante restaurante) {
        return this.repository.save(new RestauranteJpaEntity(restaurante)).toRestaurante();
    }

    @Override
    public List<Restaurante> buscarComParametros(BuscarRestauranteInput input) {
        final var exemploDeRestaurante = new RestauranteJpaEntity(input).toExample();
        return this.repository.findAll(exemploDeRestaurante).stream().map(RestauranteJpaEntity::toRestaurante).toList();
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
