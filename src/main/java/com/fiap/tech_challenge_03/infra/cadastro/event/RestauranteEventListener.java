package com.fiap.tech_challenge_03.infra.cadastro.event;

import com.fiap.tech_challenge_03.domain.cadastro.event.EventoRestauranteCadastrado;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RestauranteEventListener {

    @EventListener
    public void restauranteCadastrado(final EventoRestauranteCadastrado evento) {
        log.info("Restaurante cadastrado com sucesso, id: {}", evento.getId());
    }
}
