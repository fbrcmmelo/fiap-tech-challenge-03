package com.fiap.tech_challenge_03.infra.avaliacao.gateway;

import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.infra.avaliacao.entity.AvaliacaoEntity;
import com.fiap.tech_challenge_03.infra.avaliacao.repository.AvaliacaoMongoRepository;
import com.fiap.tech_challenge_03.utils.AvaliacaoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvaliacaoGatewayImplTest {

    @Mock
    private AvaliacaoMongoRepository repository;

    @InjectMocks
    private AvaliacaoGatewayImpl avaliacaoGateway;

    private Avaliacao avaliacao;
    private AvaliacaoEntity avaliacaoEntity;

    @BeforeEach
    void setUp() {
        avaliacao = AvaliacaoBuilder.entity();
        avaliacaoEntity = new AvaliacaoEntity(avaliacao);
    }

    @Test
    void deveCadastrarAvaliacao() {
        // Arrange
        when(repository.save(avaliacaoEntity)).thenReturn(avaliacaoEntity);

        // Act
        Avaliacao result = avaliacaoGateway.cadastrar(avaliacao);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getNota().getValorNota()).isEqualTo(avaliacao.getNota().getValorNota());
        assertThat(result.getComentario()).isEqualTo(avaliacao.getComentario());
        verify(repository, times(1)).save(avaliacaoEntity);
    }
}