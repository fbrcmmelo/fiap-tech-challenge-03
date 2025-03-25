package com.fiap.tech_challenge_03.infra.cadastro.gateway;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.infra.cadastro.entity.RestauranteJpaEntity;
import com.fiap.tech_challenge_03.infra.cadastro.repository.RestauranteMongoRepository;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestauranteGatewayImplTest {

    @Mock
    private RestauranteMongoRepository repository;

    @InjectMocks
    private RestauranteGatewayImpl gateway;

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        this.openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown()
            throws Exception {
        this.openMocks.close();
    }

    @Nested
    class ResgistroRestaurante {

        @Test
        void deveCadastrarRestauranteNoRepositorio() {
            // Arrange
            final var entity = RestauranteBuilder.entity();
            when(repository.save(any(RestauranteJpaEntity.class))).thenReturn(RestauranteBuilder.jpaEntity());

            // Act
            final var entitySaved = gateway.cadastrar(entity);

            // Assert
            assertThat(entitySaved).isInstanceOf(Restaurante.class).isNotNull();
            assertThat(entity.getId()).isEqualTo(entitySaved.getId());
            assertThat(entity.getNome().nome()).isEqualTo(entitySaved.getNome().nome());
            assertThat(entity.getQuantidadeMesas()).isEqualTo(entitySaved.getQuantidadeMesas());
            assertThat(entity.getLocalidade().getLougradouro()).isEqualTo(entitySaved.getLocalidade().getLougradouro());
            assertThat(entity.getLocalidade().getCidade()).isEqualTo(entitySaved.getLocalidade().getCidade());
            assertThat(entity.getLocalidade().getEstado()).isEqualTo(entitySaved.getLocalidade().getEstado());
            assertThat(entity.getLocalidade().getNumero()).isEqualTo(entitySaved.getLocalidade().getNumero());
            assertThat(entity.getFuncionamento().getHoraInicial()).isEqualTo(
                    entitySaved.getFuncionamento().getHoraInicial());
            assertThat(entity.getFuncionamento().getHoraFinal()).isEqualTo(
                    entitySaved.getFuncionamento().getHoraFinal());
            assertThat(entity.getFuncionamento().getDiasDaSemana()).isEqualTo(
                    entitySaved.getFuncionamento().getDiasDaSemana());
            assertThat(entity.getTipoDeCozinha()).isEqualTo(entitySaved.getTipoDeCozinha());

            verify(repository, times(1)).save(any(RestauranteJpaEntity.class));
        }
    }

    @Nested
    class BuscaRestaurante {

        @Test
        void deveBuscarRestaurantesEquivalentesPesquisa() {
            // Arrange
            final var input = RestauranteBuilder.buscarComParametrosInput();
            final var entity = RestauranteBuilder.entity();

            RestauranteJpaEntity jpaEntity = new RestauranteJpaEntity(entity);
            when(repository.findAll(any(Example.class))).thenReturn(Collections.singletonList(jpaEntity));

            // Act
            final var restaurantes = gateway.buscarComParametros(input);

            assertThat(restaurantes)
                    .isNotNull()
                    .hasAtLeastOneElementOfType(Restaurante.class)
                    .hasSize(1);
            assertThat(restaurantes.iterator().next().getId())
                    .isEqualTo(jpaEntity.getId());
            assertThat(restaurantes.iterator().next().getNome().nome())
                    .isEqualTo(jpaEntity.getNome());
        }
    }
}
