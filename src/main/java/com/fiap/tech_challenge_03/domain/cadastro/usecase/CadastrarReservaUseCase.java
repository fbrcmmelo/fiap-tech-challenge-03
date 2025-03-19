package com.fiap.tech_challenge_03.domain.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.dto.input.CadastrarReservaInput;
import com.fiap.tech_challenge_03.application.cadastro.dto.output.ReservaOutput;
import com.fiap.tech_challenge_03.application.cadastro.usecase.ICadastrarReservaUseCase;
import com.fiap.tech_challenge_03.domain.cadastro.service.ReserveDomainService;
import com.fiap.tech_challenge_03.domain.cadastro.service.RestauranteDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class CadastrarReservaUseCase implements ICadastrarReservaUseCase {

    private final ReserveDomainService reservaService;
    private final RestauranteDomainService restauranteService;

    public CadastrarReservaUseCase(ReserveDomainService reservaService, RestauranteDomainService restauranteService, UsuarioDomainService usuarioService) {
        this.reservaService = reservaService;
        this.restauranteService = restauranteService;
        this.usuarioService = usuarioService;
    }

    @Override
    public ReservaOutput execute(final CadastrarReservaInput input) {
        var usuario = usuarioService.buscarPorId(input.usuarioId());
        var restaurante = restauranteService.buscarPorId(input.restauranteId());

        if (restaurante.getCapacidade() < input.numeroPessoas()) {
            throw new IllegalArgumentException("Capacidade insuficiente no restaurante para essa reserva.");
        }

        var reservasNoHorario = reservaService.buscarReservasPorHorario(restaurante, input.data());
        int totalPessoasReservadas = reservasNoHorario.stream().mapToInt(Reserva::getNumeroPessoas).sum();

        if (totalPessoasReservadas + input.numeroPessoas() > restaurante.getCapacidade()) {
            throw new IllegalArgumentException("Não há espaço suficiente no restaurante nesse horário.");
        }

        var reserva = new Reserva(usuario, restaurante, input.data(), input.numeroPessoas(), StatusReserva.PENDENTE);
        reserva = reservaService.cadastrar(reserva);
        return ReservaOutput.from(reserva);
    }
}
