package com.fiap.tech_challenge_03.domain.cadastro.service;

import com.fiap.tech_challenge_03.application.cadastro.dto.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.dto.output.RestauranteOutput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.event.EventoRestauranteCadastrado;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IRestauranteGateway;
import com.fiap.tech_challenge_03.infra.interfaces.IDomainEventPub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RestauranteDomainService {

    private final IRestauranteGateway gateway;
    private final IDomainEventPub eventPub;

    public Restaurante cadastrar(Restaurante restaurante) {
        final var restauranteCadastrado = this.gateway.cadastrar(restaurante);
        this.eventPub.publish(new EventoRestauranteCadastrado(restauranteCadastrado.getId()));

        return restauranteCadastrado;
    }

    public Collection<RestauranteOutput> buscaComParametros(BuscarRestauranteInput input) {
        final var restaurantes = this.gateway.buscarComParametros(input);
        return restaurantes.stream().map(RestauranteOutput::from).toList();
    }
}
