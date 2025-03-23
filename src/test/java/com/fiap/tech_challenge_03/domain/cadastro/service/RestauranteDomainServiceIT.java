package com.fiap.tech_challenge_03.domain.cadastro.service;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
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

    @Test
    void deveBuscarRestaurantesComParametrosDeBuscaEquivalente() {
        // Arrange
        final var restaurante = RestauranteBuilder.entity();
        final var restauranteParaPesquisa = this.restauranteDomainService.cadastrar(restaurante);
        final var parametros = BuscarRestauranteInput.builder()
                .nome(restauranteParaPesquisa.getNome().nome())
                .build();

        // Act
        final var restaurantes = restauranteDomainService.buscarComParametros(parametros);

        // Assert
        assertThat(restaurantes)
                .isNotNull()
                .hasAtLeastOneElementOfType(Restaurante.class)
                .anySatisfy(r -> {
                    assertThat(r.getNome().nome())
                            .isEqualTo(parametros.nome());
                });
    }
}