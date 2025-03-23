package com.fiap.tech_challenge_03.domain.cadastro.vo;

import com.fiap.tech_challenge_03.domain.DomainException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FuncionamentoTest {

    @Test
    void devePermitirCriarFuncionamento() {
        // Arrange
        String horaInicial = "08:00";
        String horaFinal = "22:00";
        Set<Integer> diasDaSemana = new HashSet<>();
        diasDaSemana.add(1); // Monday
        diasDaSemana.add(5); // Friday

        // Act
        Funcionamento funcionamento = new Funcionamento(horaInicial, horaFinal, diasDaSemana);

        // Assert
        assertThat(funcionamento).isNotNull();
        assertThat(funcionamento.getHoraInicial()).isEqualTo(horaInicial);
        assertThat(funcionamento.getHoraFinal()).isEqualTo(horaFinal);
        assertThat(funcionamento.getDiasDaSemana()).isEqualTo(diasDaSemana);
    }

    @Test
    void deveLancarExcecaoQuandoPassarInvalidHoraInicialFormat() {
        // Act & Assert
        HashSet<Integer> diasDaSemana = new HashSet<>();
        assertThatThrownBy(() -> new Funcionamento("08:00AM", "22:00", diasDaSemana))
                .isInstanceOf(DomainException.class)
                .hasMessage("Horarios de funcionamentos inválidos");
    }

    @Test
    void deveLancarExcecaoQuandoPassarInvalidHoraFinalFormat() {
        // Act & Assert
        HashSet<Integer> diasDaSemana = new HashSet<>();
        assertThatThrownBy(() -> new Funcionamento("08:00", "10:60", diasDaSemana))
                .isInstanceOf(DomainException.class)
                .hasMessage("Horarios de funcionamentos inválidos");
    }

    @Test
    void deveLancarExcecaoQuandoPassarInvalidDiaDaSemana() {
        // Act & Assert
        Set<Integer> diasDaSemana = new HashSet<>();
        diasDaSemana.add(0); // Invalid day
        assertThatThrownBy(() -> new Funcionamento("08:00", "22:00", diasDaSemana))
                .isInstanceOf(DomainException.class)
                .hasMessage("Dias da semana inválidos, os dias devem ser de 1 a 7");

        diasDaSemana.clear();
        diasDaSemana.add(8); // Invalid day
        assertThatThrownBy(() -> new Funcionamento("08:00", "22:00", diasDaSemana))
                .isInstanceOf(DomainException.class)
                .hasMessage("Dias da semana inválidos, os dias devem ser de 1 a 7");
    }

    @Test
    void deveLancarExcecaoQuandoPassarEmptyDiasDaSemana() {
        // Act & Assert
        Set<Integer> diasDaSemana = new HashSet<>();
        Funcionamento funcionamento = new Funcionamento("08:00", "22:00", diasDaSemana);

        // Assert
        assertThat(funcionamento.getDiasDaSemana()).isEmpty();
    }
}