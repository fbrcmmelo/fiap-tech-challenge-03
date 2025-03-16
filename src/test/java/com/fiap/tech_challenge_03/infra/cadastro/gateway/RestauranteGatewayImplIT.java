package com.fiap.tech_challenge_03.infra.cadastro.gateway;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestauranteGatewayImplIT {

    @Autowired
    private RestauranteGatewayImpl gateway;

    @Test
    void deveCadastrarRestauranteNoRepositorio() {
        // Arrange
        final var entity = RestauranteBuilder.entity();

        // Act
        final var entitySaved = this.gateway.cadastrar(entity);

        // Assert
        assertThat(entitySaved)
                .isInstanceOf(Restaurante.class)
                .isNotNull();
        assertThat(entity.getNome()).isEqualTo(entitySaved.getNome());
        assertThat(entity.getCapacidade()).isEqualTo(entitySaved.getCapacidade());
        assertThat(entity.getLocalidade().getLougradouro()).isEqualTo(entitySaved.getLocalidade().getLougradouro());
        assertThat(entity.getLocalidade().getCidade()).isEqualTo(entitySaved.getLocalidade().getCidade());
        assertThat(entity.getLocalidade().getEstado()).isEqualTo(entitySaved.getLocalidade().getEstado());
        assertThat(entity.getLocalidade().getNumero()).isEqualTo(entitySaved.getLocalidade().getNumero());
        assertThat(entity.getFuncionamento().getHoraInicial()).isEqualTo(
                entitySaved.getFuncionamento().getHoraInicial());
        assertThat(entity.getFuncionamento().getHoraFinal()).isEqualTo(entitySaved.getFuncionamento().
                getHoraFinal());
        assertThat(entity.getFuncionamento().getDiasDaSemana()).isEqualTo(entitySaved.getFuncionamento().
                getDiasDaSemana());
        assertThat(entity.getTipoDeCozinha()).isEqualTo(entitySaved.getTipoDeCozinha());
    }
}