package com.fiap.tech_challenge_03.domain.cadastro.entity;

import com.fiap.tech_challenge_03.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class UsuarioTest {

    @Test
    void construtorCom_NullNome_deveLancarNullPointerException() {
        // Act & Assert
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Usuario("1", null, "12345678900", "user@example.com", "password1"))
                .withMessage("nome is marked non-null but is null");
    }

    @Test
    void construtorCom_NullCpf_deveLancarNullPointerException() {
        // Act & Assert
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Usuario("1", "Nome", null, "user@example.com", "password1"))
                .withMessage("cpf is marked non-null but is null");
    }

    @Test
    void construtorCom_NullEmail_deveLancarNullPointerException() {
        // Act & Assert
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Usuario("1", "Nome", "12345678900", null, "password1"))
                .withMessage("email is marked non-null but is null");
    }

    @Test
    void construtorCom_NullSenha_deveLancarNullPointerException() {
        // Act & Assert
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Usuario("1", "Nome", "12345678900", "user@example.com", null))
                .withMessage("senha is marked non-null but is null");
    }

    @Test
    void construtorCom_BlankNome_ShouldThrowDomainException() {
        // Act & Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> new Usuario("1", "", "12345678900", "user@example.com", "password1"))
                .withMessage("Nome usuario não pode estar em branco");
    }

    @Test
    void construtorCom_ShortNome_ShouldThrowDomainException() {
        // Act & Assert
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> new Usuario("1", "Ab", "12345678900", "user@example.com", "password1"))
                .withMessage("Nome usuario não pode ter menos que 3 characteres");
    }

    @Test
    void deveCriarUsuarioValido() {
        // Arrange
        String id = "1";
        String nome = "Usuario Teste";
        String cpf = "12345678900";
        String email = "user@example.com";
        String senha = "password1";

        // Act
        Usuario usuario = new Usuario(id, nome, cpf, email, senha);

        // Assert
        assertThat(usuario.getId())
                .isEqualTo(id);
        assertThat(usuario.getNome())
                .isEqualTo(nome);
        assertThat(usuario.getEmail())
                .isNotNull();
        assertThat(usuario.getCpf())
                .isNotNull();
        assertThat(usuario.getSenha())
                .isEqualTo(senha);
    }
}