package com.fiap.tech_challenge_03.infra.avaliacao.gateway;

import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.infra.avaliacao.repository.AvaliacaoMongoRepository;
import com.fiap.tech_challenge_03.utils.AvaliacaoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AvaliacaoGatewayImplIT {

    @Autowired
    private AvaliacaoMongoRepository repository;

    @Autowired
    private AvaliacaoGatewayImpl avaliacaoGateway;

    private Avaliacao avaliacao;

    @BeforeEach
    void setUp() {
        avaliacao = AvaliacaoBuilder.entity();
    }

    @Test
    void deveCadastrarAvaliacao() {
        // Act
        //TODO: cadastrar restaurante e avaliador para gerar uma avaliacao
        Avaliacao result = avaliacaoGateway.cadastrar(avaliacao);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getNota()).isEqualTo(avaliacao.getNota());
        assertThat(result.getComentario()).isEqualTo(avaliacao.getComentario());
        assertThat(repository.findById(result.getId())).isPresent();
    }
}