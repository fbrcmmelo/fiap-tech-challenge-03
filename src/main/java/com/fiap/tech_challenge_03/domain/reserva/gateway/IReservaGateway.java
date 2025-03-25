package com.fiap.tech_challenge_03.domain.reserva.gateway;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.reserva.entity.Reserva;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IReservaGateway {

    Reserva cadastrar(Reserva reserva);

    List<Reserva> buscarReservasPorHorario(Long restauranteId, LocalDateTime data);

    List<Reserva> buscarTodas();

    Optional<Reserva> buscarPorId(String id);

    void deletar(String id);
}
