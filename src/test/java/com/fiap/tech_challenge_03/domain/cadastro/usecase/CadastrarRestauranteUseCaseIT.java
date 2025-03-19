package com.fiap.tech_challenge_03.domain.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.output.RestauranteOutput;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CadastrarRestauranteUseCaseIT {

    @Autowired
    private CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

    @Test
    void deveExecutarCasoDeUsoCadastrarRestaurante() {
        // Arrange
        final var input = RestauranteBuilder.cadastroInput();

        // Act
        var output = cadastrarRestauranteUseCase.execute(input);

        // Assert
        assertThat(output)
                .isInstanceOf(RestauranteOutput.class)
                .isNotNull();
        assertThat(input.nome()).isEqualTo(output.nome());
        assertThat(input.capacidade()).isEqualTo(output.capacidade());
        assertThat(input.cidade()).isEqualTo(output.cidade());
        assertThat(input.estado()).isEqualTo(output.estado());
        assertThat(input.tipoDeCozinha()).isEqualTo(output.tipoDeCozinha());
    }
}