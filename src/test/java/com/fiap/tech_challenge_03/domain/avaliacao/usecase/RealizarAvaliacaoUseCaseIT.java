package com.fiap.tech_challenge_03.domain.avaliacao.usecase;

import com.fiap.tech_challenge_03.application.avaliacao.input.RealizarAvaliacaoInput;
import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.infra.cadastro.gateway.RestauranteGatewayImpl;
import com.fiap.tech_challenge_03.infra.cadastro.gateway.UsuarioGatewayImpl;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@ActiveProfiles("test")
@SpringBootTest
class RealizarAvaliacaoUseCaseIT {

    @Autowired
    private RealizarAvaliacaoUseCase realizarAvaliacaoUseCase;

    @Autowired
    private RestauranteGatewayImpl restauranteGateway;
    @Autowired
    private UsuarioGatewayImpl usuarioGateway;

    @Test
    void deveExecutarCasoDeUsoRalizarAvaliacaoUseCaseComSucesso() {
        // Arrange
        final var restauranteCadastrado = restauranteGateway.cadastrar(RestauranteBuilder.entity());
        final var usuarioCadastrado = usuarioGateway.cadastrar(UsuarioBuilder.entity());
        final var input = RealizarAvaliacaoInput.builder()
                .nota(5)
                .comentario("Teste comentado")
                .restauranteId(restauranteCadastrado.getId())
                .usuarioId(usuarioCadastrado.getId())
                .build();

        // Act
        final var output = realizarAvaliacaoUseCase.execute(input);

        // Assert
        assertThat(output).isNotNull();
    }

    @Test
    void deveFalharCasoDeUsoRealizarAvaliacaoQuandoRestauranteNaoEncontrado() {
        // Assume invalid restaurant ID
        final var input = RealizarAvaliacaoInput.builder()
                .restauranteId("invalid-id")
                .build();

        // Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> realizarAvaliacaoUseCase.execute(input))
                .withMessageContaining("Restaurante de id invalid-id não encontrado para avaliação");
    }

    @Test
    void deveFalharCasoDeUsoRealizarAvaliacaoQuandoUsuarioNaoEncontrado() {
        // Assume valid restaurant ID but invalid user ID
        final var restauranteCadastrado = restauranteGateway.cadastrar(RestauranteBuilder.entity());
        final var input = RealizarAvaliacaoInput.builder()
                .restauranteId(restauranteCadastrado.getId())
                .usuarioId("invalid-id")
                .build();

        // Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> realizarAvaliacaoUseCase.execute(input))
                .withMessageContaining("Usuario avaliador de id invalid-id não encontrado para avaliação");
    }
}
