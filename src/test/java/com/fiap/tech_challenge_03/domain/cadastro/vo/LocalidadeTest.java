package com.fiap.tech_challenge_03.domain.cadastro.vo;

import com.fiap.tech_challenge_03.infra.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocalidadeTest {

    @Test
    void devePermitirCriarLocalidade() {
        // Arrange
        Integer numero = 123;
        String lougradouro = "Rua Teste";
        String cidade = "Cidade Teste";
        String estado = "Estado Teste";

        // Act
        Localidade localidade = new Localidade(numero, lougradouro, cidade, estado);

        // Assert
        assertThat(localidade).isNotNull();
        assertThat(localidade.getNumero()).isEqualTo(numero);
        assertThat(localidade.getLougradouro()).isEqualTo(lougradouro);
        assertThat(localidade.getCidade()).isEqualTo(cidade);
        assertThat(localidade.getEstado()).isEqualTo(estado);
    }

    @Test
    void deveLancarExcecaoQuandoPassarNullNumero() {
        // Act & Assert
        assertThatThrownBy(() -> new Localidade(null, "Rua Teste", "Cidade Teste", "Estado Teste"))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Numero não pode estar null");
    }

    @Test
    void deveLancarExcecaoQuandoPassarNullLougradouro() {
        // Act & Assert
        assertThatThrownBy(() -> new Localidade(123, null, "Cidade Teste", "Estado Teste"))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Logradouro não pode estar null");
    }

    @Test
    void deveLancarExcecaoQuandoPassarNullCidade() {
        // Act & Assert
        assertThatThrownBy(() -> new Localidade(123, "Rua Teste", null, "Estado Teste"))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Cidade não pode estar null");
    }

    @Test
    void deveLancarExcecaoQuandoPassarNullEstado() {
        // Act & Assert
        assertThatThrownBy(() -> new Localidade(123, "Rua Teste", "Cidade Teste", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Estado não pode estar null");
    }

    @Test
    void deveLancarExcecaoQuandoPassarEmptyLougradouro() {
        // Act & Assert
        assertThatThrownBy(() -> new Localidade(123, "", "Cidade Teste", "Estado Teste"))
                .isInstanceOf(DomainException.class)
                .hasMessage("Logradouro não pode estar em branco");
    }

    @Test
    void deveLancarExcecaoQuandoPassarEmptyCidade() {
        // Act & Assert
        assertThatThrownBy(() -> new Localidade(123, "Rua Teste", "", "Estado Teste"))
                .isInstanceOf(DomainException.class)
                .hasMessage("Cidade não pode estar em branco");
    }

    @Test
    void deveLancarExcecaoQuandoPassarEmptyEstado() {
        // Act & Assert
        assertThatThrownBy(() -> new Localidade(123, "Rua Teste", "Cidade Teste", ""))
                .isInstanceOf(DomainException.class)
                .hasMessage("Estado não pode estar em branco");
    }
}