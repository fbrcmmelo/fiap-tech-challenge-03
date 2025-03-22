package com.fiap.tech_challenge_03.domain.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.domain.cadastro.service.RestauranteDomainService;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BuscarRestaurantesUseCaseTest {

    @Mock
    private RestauranteDomainService restauranteDomainService;

    @InjectMocks
    private BuscarRestaurantesComParametrosUseCase buscarRestaurantesUseCase;

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        this.openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown()
            throws Exception {
        openMocks.close();
    }

    @Test
    void deveExecutarCasoDeUsoBuscarRestaurantesComParametros() {
        // Arrange
        final var input = RestauranteBuilder.buscarComParametrosInput();
        final var entity = RestauranteBuilder.entity();

        when(restauranteDomainService.buscarComParametros(any(BuscarRestauranteInput.class))).thenReturn(
                Collections.singletonList(entity));

        // Act
        var outputList = buscarRestaurantesUseCase.execute(input);

        // Assert
        assertThat(outputList)
                .isNotNull()
                .hasSize(1);
        assertThat(outputList.iterator().next().nome())
                .isEqualTo(entity.getNome().nome());

        verify(restauranteDomainService, times(1)).buscarComParametros(any(BuscarRestauranteInput.class));

    }

    @Test
    void deveLancarExcecaoQuandoBuscarRestaurantesComObjetoDePesquisaNulo() {
        // Arrange
        BuscarRestauranteInput input = null;

        // Act And Assert
        assertThatThrownBy(() -> buscarRestaurantesUseCase.execute(input))
                .isInstanceOf(DomainException.class)
                .hasMessage("Busca precisa ter ao menos um parâmetro");

        verify(restauranteDomainService, never()).buscarComParametros(input);
    }
}