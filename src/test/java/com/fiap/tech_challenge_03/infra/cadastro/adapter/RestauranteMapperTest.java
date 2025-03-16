package com.fiap.tech_challenge_03.infra.cadastro.adapter;

import com.fiap.tech_challenge_03.application.cadastro.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RestauranteMapperTest {

    private RestauranteMapper mapper;

    @Test
    void deveConverterParaRestauranteDTO() {
        // Arrange
        final var output = RestauranteBuilder.output();

        // Act
        final var dto = mapper.dtoFrom(output);

        // Assert
        assertThat(dto)
                .isInstanceOf(RestauranteDTO.class)
                .isNotNull();
        assertThat(dto.getId())
                .isEqualTo(output.id());
        assertThat(dto.getNome())
                .isEqualTo(output.nome());
    }
}