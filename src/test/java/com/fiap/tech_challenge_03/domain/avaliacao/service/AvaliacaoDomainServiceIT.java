package com.fiap.tech_challenge_03.domain.avaliacao.service;

import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.utils.AvaliacaoBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class AvaliacaoDomainServiceIT {

    @Autowired
    private AvaliacaoDomainService avaliacaoDomainService;

    @Test
    void deveCadastrarAvaliacaoEEnviarEnventoAvaliacaoRealizada() {
        // Arrange
        final var avaliacao = AvaliacaoBuilder.entity();

        // Act
        final var result = avaliacaoDomainService.realizar(avaliacao);

        // Assert
        assertThat(result)
                .isNotNull()
                .isInstanceOf(Avaliacao.class);
        assertThat(result.getNota().getValorNota())
                .isEqualTo(avaliacao.getNota().getValorNota());
    }
}
