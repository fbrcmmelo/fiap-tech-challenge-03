package com.fiap.tech_challenge_03.infra.avaliacao.api;

import com.fiap.tech_challenge_03.application.avaliacao.output.AvaliacaoOutput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.vo.NomeRestaurante;
import com.fiap.tech_challenge_03.infra.avaliacao.api.dto.AvaliacaoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class AvaliacaoMapperTest {

    @Mock
    private AvaliacaoOutput avaliacaoOutputMock;
    @Mock
    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConverParaDTO() {
        // Arrange
        when(avaliacaoOutputMock.id()).thenReturn("1L");
        when(avaliacaoOutputMock.nota()).thenReturn(5);
        when(avaliacaoOutputMock.comentario()).thenReturn("Excellent!");

        when(avaliacaoOutputMock.restaurante()).thenReturn(restaurante);
        when(restaurante.getId()).thenReturn("1L");
        when(restaurante.getNome()).thenReturn(new NomeRestaurante("Pasta House"));

        // Act
        AvaliacaoDTO result = AvaliacaoMapper.dtoFrom(avaliacaoOutputMock);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1L");
        assertThat(result.getNota()).isEqualTo(5);
        assertThat(result.getComentario()).isEqualTo("Excellent!");
        assertThat(result.getRestauranteDto()).isNotNull();
        assertThat(result.getRestauranteDto().getId()).isEqualTo("1L");
        assertThat(result.getRestauranteDto().getNome()).isEqualTo("Pasta House");
    }
}
