package com.fiap.tech_challenge_03.infra.cadastro.gateway;

import com.fiap.tech_challenge_03.infra.cadastro.entity.UsuarioJpaEntity;
import com.fiap.tech_challenge_03.infra.cadastro.repository.UsuarioMongoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UsuarioGatewayImplIT {

    @Autowired
    private UsuarioMongoRepository repository;

    @Autowired
    private UsuarioGatewayImpl usuarioGateway;

    @Test
    void deveBuscarUsuarioPorIdEEcontrarUmUsuario() {
        // Arrange
        final var usuarioJpaEntity = new UsuarioJpaEntity();
        usuarioJpaEntity.setId("123");

        repository.save(usuarioJpaEntity);

        // Act
        final var usuarioOptional = usuarioGateway.buscarPorId("123");

        // Assert
        assertThat(usuarioOptional).isPresent();
        assertThat(usuarioOptional.get().getId()).isEqualTo("123");
    }

    @Test
    void deveBuscarUsuarioPorIdERotornarVazio() {
        // Act
        final var usuarioOptional = usuarioGateway.buscarPorId("non-existent-id");

        // Assert
        assertThat(usuarioOptional).isNotPresent();
    }
}