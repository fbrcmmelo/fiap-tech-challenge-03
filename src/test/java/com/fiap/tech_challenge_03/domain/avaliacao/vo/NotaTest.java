package com.fiap.tech_challenge_03.domain.avaliacao.vo;

import com.fiap.tech_challenge_03.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class NotaTest {

    @Test
    void testConstructor_NullNota_ShouldThrowNullPointerException() {
        // Act & Assert
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Nota(null))
                .withMessage("Nota não pode ser nula");
    }

    @Test
    void testConstructor_ValorNotaInvalido_MenorOuIgualAZero_ShouldThrowDomainException() {
        // Act & Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> new Nota(0))
                .withMessage("Valor da nota inválida. Nota dever ser de 0 a 5");

        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> new Nota(-1))
                .withMessage("Valor da nota inválida. Nota dever ser de 0 a 5");
    }

    @Test
    void testConstructor_ValorNotaInvalido_MaiorQueCinco_ShouldThrowDomainException() {
        // Act & Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> new Nota(6))
                .withMessage("Valor da nota inválida. Nota dever ser de 0 a 5");
    }

    @Test
    void testConstructor_ValorNotaValido_ShouldSetValorNota() {
        // Arrange
        Integer validValorNota = 4;

        // Act
        Nota nota = new Nota(validValorNota);

        // Assert
        assertThat(nota.getValorNota()).isEqualTo(validValorNota);
    }
}