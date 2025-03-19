package com.fiap.tech_challenge_03.domain.cadastro.gateway;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Reserva;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;

import java.time.LocalDateTime;
import java.util.List;

public interface IReservaGateway {

    Reserva cadastrar(Reserva reserva);

    List<Reserva> buscarReservasPorHorario(Restaurante restaurante, LocalDateTime data);
}
