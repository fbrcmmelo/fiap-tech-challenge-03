package com.fiap.tech_challenge_03.domain.reserva.event;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class EventoReservaCadastradaTest {

    @Test
    void deveCriarEventoComIdCorreto() {
        // Arrange
        String idEsperado = "123";

        // Act
        EventoReservaCadastrada evento = new EventoReservaCadastrada(idEsperado);

        // Assert
        assertThat(evento.getId()).isEqualTo(idEsperado);
    }

    @Test
    void deveRetornarToStringCorreto() {
        // Arrange & Act
        EventoReservaCadastrada evento = new EventoReservaCadastrada("123");

        // Assert
        assertThat(evento.toString()).isEqualTo("Reservar Cadastrado");
    }
}
