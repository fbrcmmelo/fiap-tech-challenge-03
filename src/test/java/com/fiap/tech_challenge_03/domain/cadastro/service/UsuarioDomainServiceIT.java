package com.fiap.tech_challenge_03.domain.cadastro.service;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class UsuarioDomainServiceIT {

    @Autowired
    private UsuarioDomainService usuarioDomainService;

    @Test
    void deveIntegrarComGatewayECadastrarUsuarioEmUmRepositorio() {
        // Arrange
        final var usuario = UsuarioBuilder.entity();

        // Act
        var entitySaved = usuarioDomainService.cadastrar(usuario);

        // Assert
        assertThat(entitySaved)
                .isInstanceOf(Usuario.class)
                .isNotNull();
        assertThat(entitySaved.getId())
                .isNotNull();
        assertThat(entitySaved.getNome())
                .isEqualTo(usuario.getNome());
    }
}
