package com.fiap.tech_challenge_03.domain.reserva.usecase;

import com.fiap.tech_challenge_03.application.IUseCaseIO;
import com.fiap.tech_challenge_03.application.reserva.enums.StatusReserva;
import com.fiap.tech_challenge_03.application.reserva.input.CadastrarReservaInput;
import com.fiap.tech_challenge_03.application.reserva.output.ReservaOutput;
import com.fiap.tech_challenge_03.application.reserva.usecase.ICadastrarReservaUseCase;
import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IRestauranteGateway;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IUsuarioGateway;
import com.fiap.tech_challenge_03.domain.reserva.entity.Reserva;
import com.fiap.tech_challenge_03.domain.reserva.service.ReservaDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor

public class CadastrarReservaUseCase implements ICadastrarReservaUseCase {

    private final ReservaDomainService reservaService;
    private final IRestauranteGateway restauranteGateway;
    private final IUsuarioGateway usuarioGateway;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"); // Ajuste o formato conforme necessário

    public ReservaOutput execute(CadastrarReservaInput input) {

        System.out.println("Restaurante ID: " + input.restauranteId());
        System.out.println("Usuário ID: " + input.usuarioId());
        System.out.println("Número de Pessoas: " + input.numeroPessoas());

        final var restaurante = this.restauranteGateway.buscarPorId(input.restauranteId());

        if (restaurante.isEmpty()) {
            throw new DomainException("Restaurante de id ".concat(input.restauranteId()).concat(" " + "não encontrado para reserva"));
        }

        final var cliente = this.usuarioGateway.buscarPorId(input.usuarioId());

        if (cliente.isEmpty()) {
            throw new DomainException("Usuario de id ".concat(input.usuarioId()).concat(" " + "não encontrado para reserva"));
        }

        LocalDateTime dataReserva = LocalDateTime.parse(input.data(), FORMATTER);

//        var reservasNoHorario = reservaService.buscarReservasPorHorario(Long.parseLong(input.restauranteId()), dataReserva);
//        int totalPessoasReservadas = reservasNoHorario.stream().mapToInt(Reserva::getNumeroPessoas).sum();
//
//        if (totalPessoasReservadas + input.numeroPessoas() > restaurante()) {
//            throw new IllegalArgumentException("Não há espaço suficiente no restaurante nesse horário.");
//        }

        var reserva = new Reserva(null ,input.usuarioId(), input.restauranteId(), dataReserva, input.numeroPessoas(), StatusReserva.PENDENTE);
        reserva = reservaService.cadastrar(reserva);
        return ReservaOutput.from(reserva);
    }
}
