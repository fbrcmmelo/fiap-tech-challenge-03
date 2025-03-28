package com.fiap.tech_challenge_03.infra.reserva.entity;

import com.fiap.tech_challenge_03.application.reserva.enums.StatusReserva;
import com.fiap.tech_challenge_03.domain.reserva.entity.Reserva;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@Data
@Document("reserva")
public class ReservaJpaEntity {

    @Id
    private String id;
    private String restauranteId;
    private String usuarioId;
    private LocalDateTime data;
    private int numeroPessoas;
    private String status;

    public ReservaJpaEntity(Reserva reserva) {
        Objects.requireNonNull(reserva);

        this.id = reserva.getId();
        this.restauranteId = reserva.getRestauranteId();
        this.usuarioId = reserva.getUsuarioId();
        this.data = reserva.getData();
        this.numeroPessoas = reserva.getNumeroPessoas();
        this.status = reserva.getStatus().name();
    }

    public Reserva toReserva() {
        return new Reserva(
                this.id,
                this.usuarioId,
                this.restauranteId,
                this.data,
                this.numeroPessoas,
                StatusReserva.valueOf(this.status)
        );
    }
}
