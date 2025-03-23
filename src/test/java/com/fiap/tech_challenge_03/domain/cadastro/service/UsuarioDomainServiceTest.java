package com.fiap.tech_challenge_03.domain.cadastro.service;

import com.fiap.tech_challenge_03.domain.IDomainEventPubGateway;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;
import com.fiap.tech_challenge_03.domain.cadastro.event.EventoUsuarioCadastrado;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IUsuarioGateway;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioDomainServiceTest {

    @Mock
    private IUsuarioGateway gateway;

    @Mock
    private IDomainEventPubGateway eventPub;

    @InjectMocks
    private UsuarioDomainService usuarioDomainService;

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
    void deveIntegrarComGatewayECadastrarUsuarioEmUmRepositorio() {
        // Arrange
        final var usuario = UsuarioBuilder.entity();
        when(gateway.cadastrar(any(Usuario.class))).thenReturn(usuario);

        // Act
        Usuario result = usuarioDomainService.cadastrar(usuario);

        // Assert
        assertNotNull(result);
        assertEquals(usuario, result);
        verify(gateway, times(1)).cadastrar(any(Usuario.class));
        verify(eventPub, times(1)).publish(any(EventoUsuarioCadastrado.class));
    }
}
