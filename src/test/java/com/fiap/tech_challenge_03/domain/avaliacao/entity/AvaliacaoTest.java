package com.fiap.tech_challenge_03.domain.avaliacao.entity;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AvaliacaoTest {

    @Test
    void testConstructor_NullNota_DeveLancarNullPointerException() {
        // Arrange
        Usuario avaliador = UsuarioBuilder.entity();
        Restaurante restaurante = RestauranteBuilder.entity();

        // Act & Assert
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Avaliacao(null, null, "Bom lugar!", avaliador, restaurante))
                .withMessage("Nota não pode ser nula");
    }

    @Test
    void testConstructor_NullAvaliador_DeveLancarNullPointerException() {
        // Arrange
        Restaurante restaurante = RestauranteBuilder.entity();

        // Act & Assert
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Avaliacao(null, 4, "Bom lugar!", null, restaurante))
                .withMessage("Avaliador não pode ser nulo");
    }

    @Test
    void testConstructor_NullRestaurante_DeveLancarNullPointerException() {
        // Arrange
        Usuario avaliador = UsuarioBuilder.entity();

        // Act & Assert
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Avaliacao(null, 4, "Bom lugar!", avaliador, null))
                .withMessage("Restaurante não pode ser nulo");
    }

    @Test
    void deveCriarAvaliacaoCorretamente() {
        // Arrange
        Integer validNota = 4;
        String comentario = "Gostei!";
        Usuario avaliador = UsuarioBuilder.entity();
        Restaurante restaurante = RestauranteBuilder.entity();

        // Act
        Avaliacao avaliacao = new Avaliacao(null, validNota, comentario, avaliador, restaurante);

        // Assert
        assertThat(avaliacao.getNota()).isNotNull();
        assertThat(avaliacao.getNota().getValorNota()).isEqualTo(validNota);
        assertThat(avaliacao.getComentario()).isEqualTo(comentario);
        assertThat(avaliacao.getAvaliador()).isEqualTo(avaliador);
        assertThat(avaliacao.getRestaurante()).isEqualTo(restaurante);
    }
}
