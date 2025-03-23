package com.fiap.tech_challenge_03.domain.avaliacao.usecase;

import com.fiap.tech_challenge_03.application.avaliacao.input.RealizarAvaliacaoInput;
import com.fiap.tech_challenge_03.application.avaliacao.output.AvaliacaoOutput;
import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.domain.avaliacao.service.AvaliacaoDomainService;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IRestauranteGateway;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IUsuarioGateway;
import com.fiap.tech_challenge_03.utils.AvaliacaoBuilder;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RealizarAvaliacaoUseCaseTest {

    @Mock
    private AvaliacaoDomainService service;

    @Mock
    private IRestauranteGateway restauranteGateway;

    @Mock
    private IUsuarioGateway usuarioGateway;

    @InjectMocks
    private RealizarAvaliacaoUseCase realizarAvaliacaoUseCase;

    private RealizarAvaliacaoInput input;
    private Avaliacao avaliacao;

    @BeforeEach
    void setUp() {
        input = AvaliacaoBuilder.realizarAvaliacaoInput();
        avaliacao = AvaliacaoBuilder.entity();
    }

    @Test
    void deveExecutarCasoDeUsoRalizarAvaliacaoUseCaseComSucesso() {
        // Arrange
        when(restauranteGateway.buscarPorId(input.restauranteId())).thenReturn(
                Optional.of(RestauranteBuilder.entity())); // Mock Restaurante
        when(usuarioGateway.buscarPorId(input.usuarioId())).thenReturn(
                Optional.of(UsuarioBuilder.entity())); // Mock Usuario
        when(service.realizar(any(Avaliacao.class))).thenReturn(avaliacao);

        // Act
        AvaliacaoOutput output = realizarAvaliacaoUseCase.execute(input);

        // Assert
        assertThat(output).isNotNull();
        verify(restauranteGateway, times(1)).buscarPorId(input.restauranteId());
        verify(usuarioGateway, times(1)).buscarPorId(input.usuarioId());
        verify(service, times(1)).realizar(any(Avaliacao.class));
    }

    @Test
    void deveFalharCasoDeUsoRealizarAvaliacaoQuandoRestauranteNaoEncontrado() {
        // Arrange
        when(restauranteGateway.buscarPorId(input.restauranteId())).thenReturn(Optional.empty());

        // Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> realizarAvaliacaoUseCase.execute(input))
                .withMessageContaining("Restaurante de id " + input.restauranteId() + " não encontrado para avaliação");

        verify(restauranteGateway, times(1)).buscarPorId(input.restauranteId());
    }

    @Test
    void deveFalharCasoDeUsoRealizarAvaliacaoQuandoUsuarioNaoEncontrado() {
        // Arrange
        when(restauranteGateway.buscarPorId(input.restauranteId())).thenReturn(
                Optional.of(RestauranteBuilder.entity()));
        when(usuarioGateway.buscarPorId(input.usuarioId())).thenReturn(Optional.empty());

        // Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> realizarAvaliacaoUseCase.execute(input))
                .withMessageContaining(
                        "Usuario avaliador de id " + input.usuarioId() + " não encontrado para avaliação");

        verify(restauranteGateway, times(1)).buscarPorId(input.restauranteId());
        verify(usuarioGateway, times(1)).buscarPorId(input.usuarioId());
    }
}
