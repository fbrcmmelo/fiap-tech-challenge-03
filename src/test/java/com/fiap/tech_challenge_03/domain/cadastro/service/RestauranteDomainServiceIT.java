package com.fiap.tech_challenge_03.domain.cadastro.service;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestauranteDomainServiceIT {

    @Autowired
    private RestauranteDomainService restauranteDomainService;

    @Test
    void deveIntegrarComGatewayECadastrarRestauranteEmUmRepositorio() {
        // Arrange
        final var restaurante = RestauranteBuilder.entity();

        // Act
        var entitySaved = restauranteDomainService.cadastrar(restaurante);

        // Assert
        assertThat(entitySaved)
                .isInstanceOf(Restaurante.class)
                .isNotNull();
    }
}