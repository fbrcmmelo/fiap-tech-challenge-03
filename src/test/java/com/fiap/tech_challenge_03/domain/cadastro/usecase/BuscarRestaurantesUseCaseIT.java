package com.fiap.tech_challenge_03.domain.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class BuscarRestaurantesUseCaseIT {

    @Autowired
    private BuscarRestaurantesComParametrosUseCase buscarRestaurantesUseCase;

    @Test
    void deveExecutarCasoDeUsoBuscarRestaurantes() {
        // Arrange
        final var input = RestauranteBuilder.buscarComParametrosInput();
        final var entity = RestauranteBuilder.entity();

        // Act
        var outputList = buscarRestaurantesUseCase.execute(input);

        // Assert
        assertThat(outputList)
                .isNotNull()
                .isEmpty();
//        assertThat(outputList.iterator().next().nome())
//                .isEqualTo(entity.getNome().nome());
        //TODO: ajustar resultado apos integracao com a repository
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