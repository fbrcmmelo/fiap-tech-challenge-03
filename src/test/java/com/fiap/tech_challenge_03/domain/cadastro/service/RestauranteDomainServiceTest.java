package com.fiap.tech_challenge_03.domain.cadastro.service;

import com.fiap.tech_challenge_03.application.cadastro.dto.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.event.EventoRestauranteCadastrado;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IRestauranteGateway;
import com.fiap.tech_challenge_03.infra.interfaces.IDomainEventPub;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestauranteDomainServiceTest {

    @Mock
    private IRestauranteGateway gateway;

    @Mock
    private IDomainEventPub eventPub;

    @InjectMocks
    private RestauranteDomainService restauranteDomainService;

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        this.openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown()
            throws Exception {
        openMocks.close();
    }

    @Test
    void deveIntegrarComGatewayECadastrarRestauranteEmUmRepositorio() {
        // Arrange
        final var restaurante = RestauranteBuilder.entity();
        when(gateway.cadastrar(any(Restaurante.class))).thenReturn(restaurante);

        // Act
        Restaurante result = restauranteDomainService.cadastrar(restaurante);

        // Assert
        assertNotNull(result);
        assertEquals(restaurante, result);
        verify(gateway, times(1)).cadastrar(any(Restaurante.class));
        verify(eventPub, times(1)).publish(any(EventoRestauranteCadastrado.class));
    }

    @Test
    void deveBuscarRestaurantesComParametrosDeBuscaEquivalente() {
        // Arrange
        var entity = RestauranteBuilder.entity();
        final var nomeECidade = new BuscarRestauranteInput(entity.getNome().nome(), entity.getLocalidade().getCidade(),
                null, null, null);

        when(gateway.buscarComParametros(nomeECidade)).thenReturn(Collections.singletonList(entity));

        // Act
        final var restaurantes = restauranteDomainService.buscarComParametros(nomeECidade);

        // Assert
        assertNotNull(restaurantes);
        assertEquals(1, restaurantes.size());
        assertEquals(nomeECidade.nome(), restaurantes.iterator().next().getNome().nome());
        assertEquals(nomeECidade.cidade(), restaurantes.iterator().next().getLocalidade().getCidade());
        verify(gateway, times(1)).buscarComParametros(nomeECidade);
    }
}