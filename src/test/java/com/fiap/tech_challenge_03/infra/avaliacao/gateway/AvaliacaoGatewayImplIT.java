package com.fiap.tech_challenge_03.infra.avaliacao.gateway;

import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.infra.cadastro.gateway.RestauranteGatewayImpl;
import com.fiap.tech_challenge_03.infra.cadastro.gateway.UsuarioGatewayImpl;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class AvaliacaoGatewayImplIT {

    @Autowired
    private AvaliacaoGatewayImpl avaliacaoGateway;
    @Autowired
    private RestauranteGatewayImpl restauranteGateway;
    @Autowired
    private UsuarioGatewayImpl usuarioGateway;

    @Test
    void deveCadastrarAvaliacao() {
        // Act
        final var usuarioCadastrado = this.usuarioGateway.cadastrar(UsuarioBuilder.entity());
        final var restauranteCadastrado = this.restauranteGateway.cadastrar(RestauranteBuilder.entity());

        final var avaliacao = new Avaliacao(null, 5, "Teste comentado", usuarioCadastrado, restauranteCadastrado);

        final var result = avaliacaoGateway.cadastrar(avaliacao);

        // Assert
        assertThat(result)
                .isNotNull()
                .isInstanceOf(Avaliacao.class);
        assertThat(result.getNota().getValorNota())
                .isEqualTo(avaliacao.getNota().getValorNota());
        assertThat(result.getComentario())
                .isEqualTo(avaliacao.getComentario());
        assertThat(avaliacao.getRestaurante().getNome())
                .isEqualTo(restauranteCadastrado.getNome());
        assertThat(avaliacao.getAvaliador().getNome())
                .isEqualTo(usuarioCadastrado.getNome());
    }
}