package com.fiap.tech_challenge_03.utils;

import com.fiap.tech_challenge_03.application.reserva.enums.StatusReserva;
import com.fiap.tech_challenge_03.application.reserva.input.CadastrarReservaInput;
import com.fiap.tech_challenge_03.application.reserva.output.ReservaOutput;
import com.fiap.tech_challenge_03.domain.reserva.entity.Reserva;

import java.time.LocalDateTime;

public class ReservaBuilder {

    private ReservaBuilder() {
        throw new IllegalStateException("Classe Utilitária");
    }

    public static Reserva entity() {
        return new Reserva("1", UsuarioBuilder.entity().getId(), RestauranteBuilder.entity().getId(),
                LocalDateTime.now(), 2, null);
    }

    public static CadastrarReservaInput cadastrarReservaInput() {
        return new CadastrarReservaInput("1", "1", LocalDateTime.now().toString(), 2);
    }

    public static ReservaOutput output() {
        return new ReservaOutput("1", UsuarioBuilder.entity().getId(), RestauranteBuilder.entity().getId(),
                LocalDateTime.now(), 2, StatusReserva.CONFIRMADA);
    }
}
