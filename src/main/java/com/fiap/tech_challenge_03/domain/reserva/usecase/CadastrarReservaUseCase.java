package com.fiap.tech_challenge_03.domain.reserva.usecase;

import com.fiap.tech_challenge_03.application.IUseCaseIO;
import com.fiap.tech_challenge_03.application.reserva.enums.StatusReserva;
import com.fiap.tech_challenge_03.application.reserva.input.CadastrarReservaInput;
import com.fiap.tech_challenge_03.application.reserva.output.ReservaOutput;
import com.fiap.tech_challenge_03.domain.cadastro.service.RestauranteDomainService;
import com.fiap.tech_challenge_03.domain.cadastro.usecase.BuscarRestaurantePorIdUseCase;
import com.fiap.tech_challenge_03.domain.reserva.entity.Reserva;
import com.fiap.tech_challenge_03.domain.reserva.service.ReservaDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class CadastrarReservaUseCase implements IUseCaseIO<CadastrarReservaInput, ReservaOutput> {

    private final ReservaDomainService reservaService;
    private final RestauranteDomainService restauranteService;
    private final BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    public ReservaOutput execute(final CadastrarReservaInput input) {
        var restaurante = buscarRestaurantePorIdUseCase.execute(input.restauranteId());

        if (restaurante.capacidade() < input.numeroPessoas()) {
            throw new IllegalArgumentException("Capacidade insuficiente no restaurante para essa reserva.");
        }

        var reservasNoHorario = reservaService.buscarReservasPorHorario(restaurante.id(), input.data());
        int totalPessoasReservadas = reservasNoHorario.stream().mapToInt(Reserva::getNumeroPessoas).sum();

        if (totalPessoasReservadas + input.numeroPessoas() > restaurante.getCapacidade()) {
            throw new IllegalArgumentException("Não há espaço suficiente no restaurante nesse horário.");
        }

        var reserva = new Reserva(input.usuarioId(), restaurante, input.data(), input.numeroPessoas(), StatusReserva.PENDENTE);
        reserva = reservaService.cadastrar(reserva);
        return ReservaOutput.from(reserva);
    }
}
