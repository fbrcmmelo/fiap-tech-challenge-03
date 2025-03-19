package com.fiap.tech_challenge_03.domain.cadastro.service;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Reserva;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IReservaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
    @RequiredArgsConstructor
public class ReserveDomainService {

    private final IReservaGateway reservaGateway;

    public Reserva cadastrar(Reserva reserva) {
        return reservaGateway.cadastrar(reserva);
    }

    public List<Reserva> buscarReservasPorHorario(Restaurante restaurante, LocalDateTime data) {
        return reservaGateway.buscarReservasPorHorario(restaurante, data);
    }
}
