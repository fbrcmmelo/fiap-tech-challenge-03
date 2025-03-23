package com.fiap.tech_challenge_03.infra.cadastro.gateway;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;
import com.fiap.tech_challenge_03.infra.cadastro.entity.UsuarioJpaEntity;
import com.fiap.tech_challenge_03.infra.cadastro.repository.UsuarioMongoRepository;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioGatewayImplTest {

    private final String userId = "123";
    @Mock
    private UsuarioMongoRepository repository;
    @InjectMocks
    private UsuarioGatewayImpl usuarioGateway;
    private UsuarioJpaEntity usuarioJpaEntity;

    @BeforeEach
    void setUp() {
        usuarioJpaEntity = new UsuarioJpaEntity(UsuarioBuilder.entity());
        usuarioJpaEntity.setId(userId);
    }

    @Test
    void testBuscarPorId_UserExists() {
        when(repository.findById(userId)).thenReturn(Optional.of(usuarioJpaEntity));

        Optional<Usuario> usuario = usuarioGateway.buscarPorId(userId);

        assertThat(usuario).isPresent();
        assertThat(usuario.get()).isNotNull(); // Adjust this assertion based on your Usuario class properties
        // Optionally, validate properties of the returned Usuario
    }

    @Test
    void testBuscarPorId_UserNotExists() {
        when(repository.findById(userId)).thenReturn(Optional.empty());

        Optional<Usuario> usuario = usuarioGateway.buscarPorId(userId);

        assertThat(usuario).isNotPresent();
    }

    @Test
    void deveCadastrarUsuario() {
        // Arrange
        final var entity = UsuarioBuilder.entity();

        when(this.repository.save(any(UsuarioJpaEntity.class))).thenReturn(new UsuarioJpaEntity(entity));

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
}