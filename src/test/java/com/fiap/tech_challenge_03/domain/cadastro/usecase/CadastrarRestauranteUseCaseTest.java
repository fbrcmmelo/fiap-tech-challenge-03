package com.fiap.tech_challenge_03.domain.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.dto.output.CadastrarRestauranteOutput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.service.RestauranteDomainService;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CadastrarRestauranteUseCaseTest {

    @Mock
    private RestauranteDomainService restauranteDomainService;

    @InjectMocks
    private CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

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
    void deveExecutarCasoDeUsoCadastrarRestaurante() {
        // Arrange
        final var input = RestauranteBuilder.cadastroInput();
        final var entity = RestauranteBuilder.entity();
        final var outputMocked = CadastrarRestauranteOutput.from(entity);

        when(restauranteDomainService.cadastrar(any(Restaurante.class))).thenReturn(entity);

        // Act
        var output = cadastrarRestauranteUseCase.execute(input);

        // Assert
        assertThat(output)
                .isInstanceOf(CadastrarRestauranteOutput.class)
                .isNotNull();
        assertThat(output.id()).isEqualTo(outputMocked.id());
        assertThat(output.nome()).isEqualTo(outputMocked.nome());
        assertThat(output.capacidade()).isEqualTo(outputMocked.capacidade());
        assertThat(output.cidade()).isEqualTo(outputMocked.cidade());
        assertThat(output.estado()).isEqualTo(outputMocked.estado());
        assertThat(output.tipoDeCozinha()).isEqualTo(outputMocked.tipoDeCozinha());

        verify(restauranteDomainService, times(1)).cadastrar(any(Restaurante.class));
    }
}