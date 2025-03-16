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

        //TODO : ajustar com mapeamento das collections ja criadas pelo Gabriel
//        when(repository.save(jpaEntity)).thenReturn(jpaEntity);

        // Act
        final var entitySaved = this.gateway.cadastrar(entity);

        // Assert
        assertThat(entitySaved)
                .isInstanceOf(Restaurante.class)
                .isNotNull();
        assertThat(entity.getId()).isEqualTo(entitySaved.getId());
        assertThat(entity.getNome().nome()).isEqualTo(entitySaved.getNome().nome());
        assertThat(entity.getCapacidade()).isEqualTo(entitySaved.getCapacidade());
        assertThat(entity.getLocalidade().getLougradouro()).isEqualTo(entitySaved.getLocalidade().getLougradouro());
        assertThat(entity.getLocalidade().getCidade()).isEqualTo(entitySaved.getLocalidade().getCidade());
        assertThat(entity.getLocalidade().getEstado()).isEqualTo(entitySaved.getLocalidade().getEstado());
        assertThat(entity.getLocalidade().getNumero()).isEqualTo(entitySaved.getLocalidade().getNumero());
        assertThat(entity.getFuncionamento().getHoraInicial()).isEqualTo(
                entitySaved.getFuncionamento().getHoraInicial());
        assertThat(entity.getFuncionamento().getHoraFinal()).isEqualTo(entitySaved.getFuncionamento().getHoraFinal());
        assertThat(entity.getFuncionamento().getDiasDaSemana()).isEqualTo(
                entitySaved.getFuncionamento().getDiasDaSemana());
        assertThat(entity.getTipoDeCozinha()).isEqualTo(entitySaved.getTipoDeCozinha());

        //TODO : ajustar com mapeamento das collections ja criadas pelo Gabriel
//        verify(repository, times(1)).save(jpaEntity);
    }
}