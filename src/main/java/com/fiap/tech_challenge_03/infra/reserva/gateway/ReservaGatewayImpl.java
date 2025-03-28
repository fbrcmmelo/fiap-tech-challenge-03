package com.fiap.tech_challenge_03.infra.reserva.gateway;

import com.fiap.tech_challenge_03.domain.reserva.entity.Reserva;
import com.fiap.tech_challenge_03.domain.reserva.gateway.IReservaGateway;
import com.fiap.tech_challenge_03.infra.reserva.entity.ReservaJpaEntity;
import com.fiap.tech_challenge_03.infra.reserva.repository.ReservaMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ReservaGatewayImpl implements IReservaGateway {

    private final ReservaMongoRepository repository;

    @Override
    public Reserva cadastrar(Reserva reserva) {
        var entity = new ReservaJpaEntity(reserva);
        var savedEntity = repository.save(entity);
        return savedEntity.toReserva();
    }

    @Override
    public List<Reserva> buscarReservasPorHorario(Long restauranteId, LocalDateTime data) {
        return List.of();
    }

    @Override
    public List<Reserva> buscarTodas() {
        return repository.findAll()
                .stream()
                .map(ReservaJpaEntity::toReserva)
                .toList();
    }

    @Override
    public Optional<Reserva> buscarPorId(String id) {
        return repository.findById(id).map(ReservaJpaEntity::toReserva);
    }

    @Override
    public void deletar(String id) {
        repository.deleteById(id);
    }
}
