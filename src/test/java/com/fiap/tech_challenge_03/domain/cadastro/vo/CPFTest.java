package com.fiap.tech_challenge_03.domain.cadastro.vo;

import com.fiap.tech_challenge_03.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CPFTest {

    @Test
    void deveLancarNullPointerExceptionComCpfNulo() {
        // Act & Assert
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new CPF(null))
                .withMessage("Cpf/Cnpj nao pode ser nulo");
    }

    @Test
    void deveLancaDomainExceptionComTamanhoCpfInvalido() {
        // Act & Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> new CPF("123")) // Invalid length
                .withMessage("Cpf/Cnpj inválido");

        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> new CPF("1234567890123456789")) // Too long
                .withMessage("Cpf/Cnpj inválido");
    }

    @Test
    void deveCriarCPFValido() {
        // Arrange
        String validCpf = "123.456.789-09";

        // Act
        CPF cpf = new CPF(validCpf);

        // Assert
        assertThat(cpf.getCpfCnpjSemFormatacao()).isEqualTo("12345678909");
        assertThat(cpf.getCpfFormatado()).isEqualTo(validCpf);
    }

    @Test
    void deveRetornarCpfFormato() {
        // Arrange
        CPF cpf = new CPF("12345678909");

        // Act
        String formattedCpf = cpf.getCpfFormatado();

        // Assert
        assertThat(formattedCpf).isEqualTo("123.456.789-09");
    }

    @Test
    void deveRetornarCnpFormatado() {
        // Arrange
        CPF cnpj = new CPF("12.345.678/0001-95");

        // Act
        String formattedCnpj = cnpj.getCpfFormatado();

        // Assert
        assertThat(formattedCnpj).isEqualTo("12.345.678/0001-95");
    }

    @Test
    void deveRetornarCPFSemFormatacao() {
        // Arrange
        CPF cpf = new CPF("123.456.789-09");

        // Act
        String unformattedCpf = cpf.getCpfCnpjSemFormatacao();

        // Assert
        assertThat(unformattedCpf).isEqualTo("12345678909");
    }

    @Test
    void deveRetornarCnpjSemFormatacao() {
        // Arrange
        CPF cnpj = new CPF("12.345.678/0001-95");

        // Act
        String unformattedCnpj = cnpj.getCpfCnpjSemFormatacao();

        // Assert
        assertThat(unformattedCnpj).isEqualTo("12345678000195");
    }
}
