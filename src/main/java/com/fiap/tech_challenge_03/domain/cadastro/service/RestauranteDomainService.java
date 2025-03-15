package com.fiap.tech_challenge_03.domain.cadastro.service;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.event.EventoRestauranteCadastrado;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.RestauranteGateway;
import com.fiap.tech_challenge_03.infra.interfaces.IDomainEventPub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestauranteDomainService {

    private final RestauranteGateway gateway;
    private final IDomainEventPub eventPub;

    public Restaurante cadastrar(Restaurante restaurante) {
        final var restauranteCadastrado = this.gateway.cadastrar(restaurante);
        this.eventPub.publish(new EventoRestauranteCadastrado(restauranteCadastrado.getId()));

        return restauranteCadastrado;
    }
}
