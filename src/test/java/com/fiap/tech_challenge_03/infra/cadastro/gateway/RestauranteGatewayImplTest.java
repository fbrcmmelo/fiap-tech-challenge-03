package com.fiap.tech_challenge_03.infra.cadastro.gateway;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.infra.cadastro.repository.RestauranteMongoRepository;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void deveCadastrarRestauranteNoRepositorio() {
        // Arrange
        final var entity = RestauranteBuilder.entity();
        final var jpaEntity = RestauranteBuilder.jpaEntity();

        //TODO : ajustar com mapeamento das collections ja criadas pelo Gabriel
//        when(repository.save(jpaEntity)).thenReturn(jpaEntity);

        // Act
        final var entitySaved = this.gateway.cadastrar(entity);

        // Assert
        assertThat(entitySaved)
                .isInstanceOf(Restaurante.class)
                .isNotNull();
        assertThat(jpaEntity.getId()).isEqualTo(entitySaved.getId());
        assertThat(jpaEntity.getNome()).isEqualTo(entitySaved.getNome());
        assertThat(jpaEntity.getCapacidade()).isEqualTo(entitySaved.getCapacidade());
        assertThat(jpaEntity.getLocalidade().getLougradouro()).isEqualTo(entitySaved.getLocalidade().getLougradouro());
        assertThat(jpaEntity.getLocalidade().getCidade()).isEqualTo(entitySaved.getLocalidade().getCidade());
        assertThat(jpaEntity.getLocalidade().getEstado()).isEqualTo(entitySaved.getLocalidade().getEstado());
        assertThat(jpaEntity.getLocalidade().getNumero()).isEqualTo(entitySaved.getLocalidade().getNumero());
        assertThat(jpaEntity.getFuncionamento().getHoraInicial()).isEqualTo(
                entitySaved.getFuncionamento().getHoraInicial());
        assertThat(jpaEntity.getFuncionamento().getHoraFinal()).isEqualTo(entitySaved.getFuncionamento().
                getHoraFinal());
        assertThat(jpaEntity.getFuncionamento().getDiasDaSemana()).isEqualTo(entitySaved.getFuncionamento().
                getDiasDaSemana());
        assertThat(jpaEntity.getTipoDeCozinha()).isEqualTo(entitySaved.getTipoDeCozinha());

        //TODO : ajustar com mapeamento das collections ja criadas pelo Gabriel
//        verify(repository, times(1)).save(jpaEntity);
    }
}