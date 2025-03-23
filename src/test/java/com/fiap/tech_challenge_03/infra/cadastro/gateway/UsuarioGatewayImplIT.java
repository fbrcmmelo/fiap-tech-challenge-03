package com.fiap.tech_challenge_03.infra.cadastro.gateway;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;
import com.fiap.tech_challenge_03.infra.cadastro.repository.UsuarioMongoRepository;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class UsuarioGatewayImplIT {

    @Autowired
    private UsuarioMongoRepository repository;

    @Autowired
    private UsuarioGatewayImpl usuarioGateway;

    @Test
    void deveCadastrarUsuario() {
        // Arrange
        final var entity = UsuarioBuilder.entity();

        // Act
        final var entitySaved = this.usuarioGateway.cadastrar(entity);

        // Assert
        assertThat(entitySaved)
                .isNotNull()
                .isInstanceOf(Usuario.class);
        assertThat(entitySaved.getId())
                .isNotNull();
        assertThat(entitySaved.getNome())
                .isEqualTo(entity.getNome());
    }

    @Test
    void deveBuscarUsuarioPorIdEEcontrarUmUsuario() {
        // Arrange
        final var usuario = UsuarioBuilder.entity();
        final var usuarioJpaEntity = this.usuarioGateway.cadastrar(usuario);

        // Act
        final var usuarioOptional = usuarioGateway.buscarPorId(usuarioJpaEntity.getId());

        // Assert
        assertThat(usuarioOptional)
                .isPresent();
        assertThat(usuarioOptional.get().getId())
                .isEqualTo(usuarioJpaEntity.getId());
    }

    @Test
    void deveBuscarUsuarioPorIdERotornarVazio() {
        // Act
        final var usuarioOptional = usuarioGateway.buscarPorId("non-existent-id");

        // Assert
        assertThat(usuarioOptional).isNotPresent();
    }

}