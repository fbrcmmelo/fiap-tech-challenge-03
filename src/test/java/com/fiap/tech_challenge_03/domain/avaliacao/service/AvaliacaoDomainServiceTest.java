package com.fiap.tech_challenge_03.domain.avaliacao.service;

import com.fiap.tech_challenge_03.domain.IDomainEventPubGateway;
import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.domain.avaliacao.event.EventoAvaliacaoRealizada;
import com.fiap.tech_challenge_03.domain.avaliacao.gateway.IAvaliacaoGateway;
import com.fiap.tech_challenge_03.utils.AvaliacaoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AvaliacaoDomainServiceTest {

    @Mock
    private IAvaliacaoGateway gateway;

    @Mock
    private IDomainEventPubGateway eventPub;

    @InjectMocks
    private AvaliacaoDomainService avaliacaoDomainService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRealizar() {
        // Arrange
        final var avaliacao = AvaliacaoBuilder.entity();
        when(gateway.cadastrar(avaliacao)).thenReturn(avaliacao);
        doNothing().when(eventPub).publish(any(EventoAvaliacaoRealizada.class));

        // Act
        Avaliacao result = avaliacaoDomainService.realizar(avaliacao);

        // Assert
        assertThat(result).isEqualTo(avaliacao);
        verify(gateway, times(1)).cadastrar(avaliacao);
        verify(eventPub, times(1)).publish(any(EventoAvaliacaoRealizada.class));
    }
}
