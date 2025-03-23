package com.fiap.tech_challenge_03.domain.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.output.RestauranteOutput;
import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@SpringBootTest
class BuscarRestaurantesUseCaseIT {

    @Autowired
    private BuscarRestaurantesComParametrosUseCase buscarRestaurantesUseCase;
    @Autowired
    private CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

    @Test
    void deveExecutarCasoDeUsoBuscarRestaurantes() {
        // Arrange
        final var output = this.cadastrarRestauranteUseCase.execute(RestauranteBuilder.cadastroInput());

        final var input = BuscarRestauranteInput.builder()
//                .nome(output.nome())
                .estado(output.localidade().getEstado())
                .build();

        // Act
        var outputList = buscarRestaurantesUseCase.execute(input);

        // Assert
        assertThat(outputList)
                .isNotNull()
                .hasAtLeastOneElementOfType(RestauranteOutput.class);
        assertThat(outputList)
                .anySatisfy(restaurante -> {
                    assertThat(restaurante.id())
                            .isEqualTo(output.id());
                });
    }

    @Test
    void deveLancarExcecaoQuandoBuscarRestaurantesComObjetoDePesquisaNulo() {
        // Arrange
        BuscarRestauranteInput input = null;

        // Act And Assert
        assertThatThrownBy(() -> buscarRestaurantesUseCase.execute(input))
                .isInstanceOf(DomainException.class)
                .hasMessage("Busca precisa ter ao menos um parâmetro");
    }
}