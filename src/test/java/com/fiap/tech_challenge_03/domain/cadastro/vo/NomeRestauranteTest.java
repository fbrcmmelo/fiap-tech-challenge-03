package com.fiap.tech_challenge_03.domain.cadastro.vo;

import com.fiap.tech_challenge_03.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NomeRestauranteTest {

    @Test
    void devePermitirCrarNomeRestaurante() {
        // Act
        NomeRestaurante nomeRestaurante = new NomeRestaurante("Restaurante Teste");

        // Assert
        assertThat(nomeRestaurante).isNotNull();
        assertThat(nomeRestaurante.nome()).isEqualTo("Restaurante Teste");
    }

    @Test
    void deveLancarExcecaoQuandoPassarNomeNull() {
        // Act & Assert
        assertThatThrownBy(() -> new NomeRestaurante(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Nome do restaurante não pode ser nulo");
    }

    @Test
    void deveLancarExcecaoQuandoPassarNomeEmBranco() {
        // Act & Assert
        assertThatThrownBy(() -> new NomeRestaurante(""))
                .isInstanceOf(DomainException.class)
                .hasMessage("Nome do restaurante não pode estar vazio");

        assertThatThrownBy(() -> new NomeRestaurante("   "))
                .isInstanceOf(DomainException.class)
                .hasMessage("Nome do restaurante não pode estar vazio");
    }
}