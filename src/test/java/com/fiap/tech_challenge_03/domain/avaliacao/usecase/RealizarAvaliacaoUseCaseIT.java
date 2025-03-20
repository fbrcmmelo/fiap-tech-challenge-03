package com.fiap.tech_challenge_03.domain.avaliacao.usecase;

import com.fiap.tech_challenge_03.domain.cadastro.gateway.IRestauranteGateway;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IUsuarioGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RealizarAvaliacaoUseCaseIT {

    @Autowired
    private RealizarAvaliacaoUseCase realizarAvaliacaoUseCase;

    @Autowired
    private IRestauranteGateway restauranteGateway;

    @Autowired
    private IUsuarioGateway usuarioGateway;

    @Test
    void deveExecutarCasoDeUsoRalizarAvaliacaoUseCaseComSucesso() {
        // Arrange
        // Create and save mock Restaurante and Usuario entities to the in-memory database.

        // Assume that you have methods in your repositories that can create these entities or you can use MongoTemplate
        // For example:
        // Restaurante savedRestaurante = restauranteRepository.save(new Restaurante(...));
        // Usuario savedUsuario = usuarioRepository.save(new Usuario(...));

//        RealizarAvaliacaoInput input = new RealizarAvaliacaoInput(savedRestaurante.getId(), savedUsuario.getId(), 5, "Ótima experiência!");
//
//        // Act
//        AvaliacaoOutput output = realizarAvaliacaoUseCase.execute(input);

        // Assert
//        assertThat(output).isNotNull();
        // Optionally assert properties of the output
    }

    @Test
    void deveFalharCasoDeUsoRealizarAvaliacaoQuandoRestauranteNaoEncontrado() {
//        // Assume invalid restaurant ID
//        RealizarAvaliacaoInput input = AvaliacaoBuilder.realizarAvaliacaoInput();
//
//        // Assert
//        assertThatExceptionOfType(DomainException.class)
//                .isThrownBy(() -> realizarAvaliacaoUseCase.execute(input))
//                .withMessageContaining("Restaurante de id invalidId não encontrado para avaliação");
    }

    @Test
    void deveFalharCasoDeUsoRealizarAvaliacaoQuandoUsuarioNaoEncontrado() {
//        // Assume valid restaurant ID but invalid user ID
//        RealizarAvaliacaoInput input = AvaliacaoBuilder.realizarAvaliacaoInput();
//
//        // Assert
//        assertThatExceptionOfType(DomainException.class)
//                .isThrownBy(() -> realizarAvaliacaoUseCase.execute(input))
//                .withMessageContaining("Usuario avaliador de id invalidUsuarioId não encontrado para avaliação");
    }
}