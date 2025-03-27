package com.fiap.tech_challenge_03.domain.reserva.service;

import com.fiap.tech_challenge_03.domain.IDomainEventPubGateway;
import com.fiap.tech_challenge_03.domain.reserva.entity.Reserva;
import com.fiap.tech_challenge_03.domain.reserva.gateway.IReservaGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReservaDomainServiceTest {

    @Mock
    private IReservaGateway reservaGateway;

    @Mock
    private IDomainEventPubGateway eventPub;

    @InjectMocks
    private ReservaDomainService reservaDomainService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarReservaCorretamente() {
        // Arrange
        Reserva reserva = mock(Reserva.class);
        when(reservaGateway.cadastrar(reserva)).thenReturn(reserva);

        // Act
        Reserva resultado = reservaDomainService.cadastrar(reserva);

        // Assert
        assertThat(resultado).isEqualTo(reserva);
        verify(reservaGateway, times(1)).cadastrar(reserva);
    }

    @Test
    void deveBuscarReservasPorHorarioCorretamente() {
        // Arrange
        Long restauranteId = 1L;
        LocalDateTime data = LocalDateTime.now();
        List<Reserva> reservasEsperadas = List.of(mock(Reserva.class));

        when(reservaGateway.buscarReservasPorHorario(restauranteId, data)).thenReturn(reservasEsperadas);

        // Act
        List<Reserva> resultado = reservaDomainService.buscarReservasPorHorario(restauranteId, data);

        // Assert
        assertThat(resultado).isEqualTo(reservasEsperadas);
        verify(reservaGateway, times(1)).buscarReservasPorHorario(restauranteId, data);
    }
}
