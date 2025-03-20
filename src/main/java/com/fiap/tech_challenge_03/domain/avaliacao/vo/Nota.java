package com.fiap.tech_challenge_03.domain.avaliacao.vo;

import com.fiap.tech_challenge_03.domain.DomainException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Nota {

    private Integer valorNota;

    public Nota(Integer valorNota) {
        Objects.requireNonNull(valorNota, "Nota não pode ser nula");

        if (valorNota > 5 || valorNota <= 0) {
            throw new DomainException("Valor da nota inválida. Nota dever ser de 0 a 5");
        }

        this.valorNota = valorNota;
    }
}
