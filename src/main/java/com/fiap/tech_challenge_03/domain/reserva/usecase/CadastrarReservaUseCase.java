package com.fiap.tech_challenge_03.domain.reserva.usecase;

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

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private final ReservaDomainService reservaService;
    private final IRestauranteGateway restauranteGateway;
    private final IUsuarioGateway usuarioGateway;

    public ReservaOutput execute(CadastrarReservaInput input) {
        final var restaurante = this.restauranteGateway.buscarPorId(input.restauranteId());

        if (restaurante.isEmpty()) {
            throw new DomainException(
                    "Restaurante de id ".concat(input.restauranteId()).concat(" " + "não encontrado para reserva"));
        }

        final var cliente = this.usuarioGateway.buscarPorId(input.usuarioId());

        if (cliente.isEmpty()) {
            throw new DomainException(
                    "Usuario de id ".concat(input.usuarioId()).concat(" " + "não encontrado para reserva"));
        }

        LocalDateTime dataReserva = LocalDateTime.parse(input.data(), FORMATTER);

        var reserva = new Reserva(null, input.usuarioId(), input.restauranteId(), dataReserva, input.numeroPessoas(),
                StatusReserva.PENDENTE);
        reserva = reservaService.cadastrar(reserva);
        return ReservaOutput.from(reserva);
    }
}
