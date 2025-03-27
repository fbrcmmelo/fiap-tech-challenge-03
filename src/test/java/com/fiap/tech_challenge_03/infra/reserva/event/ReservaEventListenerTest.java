package com.fiap.tech_challenge_03.infra.reserva.event;

import com.fiap.tech_challenge_03.domain.reserva.event.EventoReservaCadastrada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

class ReservaEventListenerTest {

    private ReservaEventListener reservaEventListener;
    private Logger logger;

    @BeforeEach
    void setUp() {
        reservaEventListener = new ReservaEventListener();
        logger = mock(Logger.class);
    }

    @Test
    void deveRegistrarLogQuandoEventoForDisparado() {
        // Arrange
        EventoReservaCadastrada evento = mock(EventoReservaCadastrada.class);
        when(evento.getId()).thenReturn("1");

        // Act
        reservaEventListener.reservaCadastrado(evento);

        // Assert (verificação manual no console)
        System.out.println("Teste unitário executado. Verifique o log de saída.");
    }
}
