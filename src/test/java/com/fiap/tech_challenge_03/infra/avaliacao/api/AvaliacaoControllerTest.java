package com.fiap.tech_challenge_03.infra.avaliacao.api;

import com.fiap.tech_challenge_03.application.avaliacao.input.RealizarAvaliacaoInput;
import com.fiap.tech_challenge_03.application.avaliacao.output.AvaliacaoOutput;
import com.fiap.tech_challenge_03.domain.avaliacao.usecase.RealizarAvaliacaoUseCase;
import com.fiap.tech_challenge_03.utils.AvaliacaoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AvaliacaoControllerTest {

    @Mock
    private RealizarAvaliacaoUseCase useCase;

    @InjectMocks
    private AvaliacaoController avaliacaoController;

    private RealizarAvaliacaoInput input;
    private AvaliacaoOutput output;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        input = AvaliacaoBuilder.realizarAvaliacaoInput();
        output = AvaliacaoBuilder.output();
    }

    @Test
    void deveRealizarCasoDeUsoERetornarOutputParaApi() {
        // Arrange
        when(useCase.execute(input)).thenReturn(output);

        // Act
        ResponseEntity<String> response = avaliacaoController.realizar(input);

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(201);
        assertThat(response.getBody()).isNotNull();
        verify(useCase, times(1)).execute(input);
    }
}