package com.fiap.tech_challenge_03.infra.cadastro.gateway;

import com.fiap.tech_challenge_03.infra.cadastro.repository.UsuarioMongoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsuarioGatewayImplIT {

    @Autowired
    private UsuarioMongoRepository repository;

    @Autowired
    private UsuarioGatewayImpl usuarioGateway;

    @Test
    @DisplayName("Should find user by ID")
    void testBuscarPorId() {
//        // Arrange
//        UsuarioJpaEntity usuarioJpaEntity = new UsuarioJpaEntity();
//        usuarioJpaEntity.setId("123");
//
//        repository.save(usuarioJpaEntity);
//
//        // Act
//        Optional<Usuario> usuarioOptional = usuarioGateway.buscarPorId("123");
//
//        // Assert
//        assertThat(usuarioOptional).isPresent();
//        assertThat(usuarioOptional.get().getId()).isEqualTo("123");
    }

    @Test
    @DisplayName("Should return empty for non-existent user")
    void testBuscarPorId_UserNotExists() {
//        // Act
//        Optional<Usuario> usuarioOptional = usuarioGateway.buscarPorId("non-existent-id");
//
//        // Assert
//        assertThat(usuarioOptional).isNotPresent();
    }
}