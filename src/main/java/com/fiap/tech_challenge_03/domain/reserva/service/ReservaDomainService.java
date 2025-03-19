package com.fiap.tech_challenge_03.domain.reserva.service;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.reserva.entity.Reserva;
import com.fiap.tech_challenge_03.domain.reserva.gateway.IReservaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
    @RequiredArgsConstructor
public class ReservaDomainService {

    private final IReservaGateway reservaGateway;

    public Reserva cadastrar(Reserva reserva) {
        return reservaGateway.cadastrar(reserva);
    }

    public List<Reserva> buscarReservasPorHorario(Long restauranteId, LocalDateTime data) {
        return reservaGateway.buscarReservasPorHorario(restauranteId, data);
    }
}
