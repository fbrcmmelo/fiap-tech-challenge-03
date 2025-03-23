package com.fiap.tech_challenge_03.infra.cadastro.adapter;

import com.fiap.tech_challenge_03.infra.cadastro.api.UsuarioMapper;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.UsuarioDTO;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UsuarioMapperTest {

    private UsuarioMapper mapper;

    @Test
    void deveConverterParaUsuarioDTO() {
        // Arrange
        final var output = UsuarioBuilder.output();

        // Act
        final var dto = mapper.dtoFrom(output);

        // Assert
        assertThat(dto)
                .isInstanceOf(UsuarioDTO.class)
                .isNotNull();
        assertThat(dto.getId())
                .isEqualTo(output.id());
        assertThat(dto.getNome())
                .isEqualTo(output.nome());
        assertThat(dto.getEmail())
                .isEqualTo(output.email().getValue());
    }
}
