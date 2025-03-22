package com.fiap.tech_challenge_03.domain.avaliacao.usecase;

import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.utils.AvaliacaoBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class RealizarAvaliacaoUseCaseIT {

    @Autowired
    private RealizarAvaliacaoUseCase realizarAvaliacaoUseCase;

    @Test
    void deveExecutarCasoDeUsoRalizarAvaliacaoUseCaseComSucesso() {
        // Arrange
        //TODO: cadastrar restaurante e avaliador para gerar uma avaliacao
        final var input = AvaliacaoBuilder.realizarAvaliacaoInput();

        // Act
        final var output = realizarAvaliacaoUseCase.execute(input);

        // Assert
        assertThat(output).isNotNull();
    }

    @Test
    void deveFalharCasoDeUsoRealizarAvaliacaoQuandoRestauranteNaoEncontrado() {
        // Assume invalid restaurant ID
        final var input = AvaliacaoBuilder.realizarAvaliacaoInput();

        // Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> realizarAvaliacaoUseCase.execute(input))
                .withMessageContaining("Restaurante de id invalidId não encontrado para avaliação");
    }

    @Test
    void deveFalharCasoDeUsoRealizarAvaliacaoQuandoUsuarioNaoEncontrado() {
        // Assume valid restaurant ID but invalid user ID
        final var input = AvaliacaoBuilder.realizarAvaliacaoInput();

        // Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> realizarAvaliacaoUseCase.execute(input))
                .withMessageContaining("Usuario avaliador de id invalidUsuarioId não encontrado para avaliação");
    }
}