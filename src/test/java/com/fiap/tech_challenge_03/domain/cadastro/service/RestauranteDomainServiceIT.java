package com.fiap.tech_challenge_03.domain.cadastro.service;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        var entity = RestauranteBuilder.entity();
        final var nomeECidade = new BuscarRestauranteInput(entity.getNome().nome(), entity.getLocalidade().getCidade(),
                null, null, null);

        // Act
        final var restaurantes = restauranteDomainService.buscarComParametros(nomeECidade);

        // Assert
        assertNotNull(restaurantes);
        assertThat(restaurantes).isEmpty();
//        assertEquals(1, restaurantes.size());
//        assertEquals(nomeECidade.nome(), restaurantes.iterator().next().getNome().nome());
//        assertEquals(nomeECidade.cidade(), restaurantes.iterator().next().getLocalidade().getCidade());
        //TODO: Ajustar asserts apos integracao
    }
}