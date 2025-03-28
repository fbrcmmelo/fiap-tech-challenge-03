package com.fiap.tech_challenge_03.domain.cadastro.vo;

import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.domain.cadastro.enums.StatusMesa;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MesaTest {

    @Test
    void deveCriarMesaWithValidTagId() {
        // Assert
        Integer validTagId = 1;

        // Act
        Mesa mesa = new Mesa(validTagId);

        // Assert
        assertThat(mesa).isNotNull();
        assertThat(mesa.getId()).isEqualTo("1");
        assertThat(mesa.getTag()).isEqualTo("Mesa-1");
        assertThat(mesa.getStatusMesa()).isEqualTo(StatusMesa.ATIVO);
    }

    @Test
    void deveLancartExceptionActTagIdIsNull() {
        // Arrange
        Integer nullTagId = null;

        // Act & Assert
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Mesa(nullTagId))
                .withMessage("Tag Id não pode ser nulo");
    }

    @Test
    void deveLancartExceptionActTagIdIsZero() {
        // Arrange
        Integer zeroTagId = 0;

        // Act & Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> new Mesa(zeroTagId))
                .withMessage("Tag id não pode ser menor ou igual a zero");
    }

    @Test
    void deveLancartExceptionActTagIdIsNegativo() {
        // Arrange
        Integer negativeTagId = -1;

        // Act & Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> new Mesa(negativeTagId))
                .withMessage("Tag id não pode ser menor ou igual a zero");
    }
}
