package com.fiap.tech_challenge_03.domain.avaliacao.event;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventoAvaliacaoRealizadaTest {

    @Test
    void deveCriarEventoAvaliacaoRealizada() {
        // Arrange
        String avaliacaoId = "12345";
        String restauranteId = "54321";

        // Act
        EventoAvaliacaoRealizada evento = new EventoAvaliacaoRealizada(avaliacaoId, restauranteId);

        // Assert
        assertThat(evento.getAvaliacaoId()).isEqualTo(avaliacaoId);
        assertThat(evento.getRestauranteId()).isEqualTo(restauranteId);
    }

    @Test
    void deveVoltarMensagemAvaliacaoRealizada() {
        // Arrange
        String avaliacaoId = "12345";
        String restauranteId = "54321";
        EventoAvaliacaoRealizada evento = new EventoAvaliacaoRealizada(avaliacaoId, restauranteId);

        // Act
        String result = evento.toString();

        // Assert
        assertThat(result).isEqualTo("Avaliacao Realizada");
    }
}