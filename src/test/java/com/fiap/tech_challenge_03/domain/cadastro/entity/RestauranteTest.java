package com.fiap.tech_challenge_03.domain.cadastro.entity;

import com.fiap.tech_challenge_03.infra.exception.DomainException;
import com.fiap.tech_challenge_03.utils.ValueObjectBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestauranteTest {

    @Test
    void deveCriarObjetoRestauranteValido() {
        // Arrange
        var localidade = ValueObjectBuilder.getLocalidade();
        var funcionamento = ValueObjectBuilder.getFuncionamento();

        // Act: Create a Restaurante object with valid parameters
        var restaurante = new Restaurante("Restaurante Teste", localidade, funcionamento, 100, "Italiana");

        // Assert: Ensure the object is created correctly
        assertThat(restaurante).isNotNull();
        assertThat(restaurante.getNome().nome()).isEqualTo("Restaurante Teste");
        assertThat(restaurante.getLocalidade()).isEqualTo(localidade);
        assertThat(restaurante.getFuncionamento()).isEqualTo(funcionamento);
        assertThat(restaurante.getCapacidade()).isEqualTo(100);
        assertThat(restaurante.getTipoDeCozinha()).isEqualTo("Italiana");
    }

    @Test
    void deveLancarExcecaoAoPassarCapacidadeInvalida() {
        // Arrange
        var localidade = ValueObjectBuilder.getLocalidade();
        var funcionamento = ValueObjectBuilder.getFuncionamento();

        // Act & Assert
        assertThatThrownBy(() -> new Restaurante("Restaurante Teste", localidade, funcionamento, 0, "Italiana"))
                .isInstanceOf(DomainException.class)
                .hasMessage("Capacidade do restaurante não pode ser menor que 1");

        assertThatThrownBy(() -> new Restaurante("Restaurante Teste", localidade, funcionamento, -1, "Italiana"))
                .isInstanceOf(DomainException.class)
                .hasMessage("Capacidade do restaurante não pode ser menor que 1");
    }

    @Test
    void deveLancarExcecaoAoPassarTipoDeCozinhaEmBranco() {
        // Arrange
        var localidade = ValueObjectBuilder.getLocalidade();
        var funcionamento = ValueObjectBuilder.getFuncionamento();
        String emptyTipoDeCozinha = "";

        // Act & Assert
        assertThrows(DomainException.class,
                () -> new Restaurante("Restaurante Teste", localidade, funcionamento, 100, emptyTipoDeCozinha));
    }

}
