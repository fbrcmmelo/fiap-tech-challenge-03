package com.fiap.tech_challenge_03.domain.avaliacao.usecase;

import com.fiap.tech_challenge_03.application.avaliacao.input.RealizarAvaliacaoInput;
import com.fiap.tech_challenge_03.application.avaliacao.output.AvaliacaoOutput;
import com.fiap.tech_challenge_03.application.avaliacao.usecase.IRealizarAvaliacaoUseCase;
import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.domain.avaliacao.service.AvaliacaoDomainService;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IRestauranteGateway;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IUsuarioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RealizarAvaliacaoUseCase implements IRealizarAvaliacaoUseCase {

    private final AvaliacaoDomainService service;
    private final IRestauranteGateway restauranteGateway;
    private final IUsuarioGateway usuarioGateway;

    @Override
    public AvaliacaoOutput execute(RealizarAvaliacaoInput input) {
        final var restaurante = this.restauranteGateway.buscarPorId(input.restauranteId());
        if (restaurante.isEmpty()) {
            throw new DomainException("Restaurante de id ".concat(input.restauranteId()).concat(" " +
                    "não encontrado para avaliação"));
        }
        final var avaliador = this.usuarioGateway.buscarPorId(input.usuarioId());
        if (avaliador.isEmpty()) {
            throw new DomainException("Usuario avaliador de id ".concat(input.usuarioId()).concat(" " +
                    "não encontrado para avaliação"));
        }

        final var avaliacaoRealizada = this.service.realizar(
                new Avaliacao(null, input.nota(), input.comentario(), avaliador.get(), restaurante.get()));

        return AvaliacaoOutput.from(avaliacaoRealizada);
    }
}
