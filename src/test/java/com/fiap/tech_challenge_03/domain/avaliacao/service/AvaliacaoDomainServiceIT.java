package com.fiap.tech_challenge_03.domain.avaliacao.service;

import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.utils.AvaliacaoBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AvaliacaoDomainServiceIT {

    @Autowired
    private AvaliacaoDomainService avaliacaoDomainService;

    @Test
    void deveCadastrarAvaliacaoEEnviarEnventoAvaliacaoRealizada() {
        // Arrange
        //TODO: cadastrar restaurante e avaliador para gerar uma avaliacao
        final var avaliacao = AvaliacaoBuilder.entity();

        // Act
        Avaliacao result = avaliacaoDomainService.realizar(avaliacao);

        // Assert
        assertThat(result).isEqualTo(avaliacao);
    }
}
