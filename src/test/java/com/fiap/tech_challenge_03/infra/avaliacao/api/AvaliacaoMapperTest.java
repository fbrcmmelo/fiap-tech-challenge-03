package com.fiap.tech_challenge_03.infra.avaliacao.api;

import com.fiap.tech_challenge_03.infra.avaliacao.api.dto.AvaliacaoDTO;
import com.fiap.tech_challenge_03.utils.AvaliacaoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

class AvaliacaoMapperTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConverParaDTO() {
        // Arrange
        final var output = AvaliacaoBuilder.output();

        // Act
        AvaliacaoDTO result = AvaliacaoMapper.dtoFrom(output);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(output.id());
        assertThat(result.getNota()).isEqualTo(output.nota());
        assertThat(result.getComentario()).isEqualTo(output.comentario());
        assertThat(result.getRestauranteDto()).isNotNull();
        assertThat(result.getRestauranteDto().getNome()).isEqualTo(output.restaurante().getNome().nome());
        assertThat(result.getAvaliadorDto().getNome()).isEqualTo(output.avaliador().getNome());
    }
}
