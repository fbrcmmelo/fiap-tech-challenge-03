package com.fiap.tech_challenge_03.domain.cadastro.vo;

import com.fiap.tech_challenge_03.infra.exception.DomainException;
import lombok.Getter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Set;

/*
 *  Value Object -> Imutável, criação de um dado consistente e integro
 *  Ajuda na construção de entidades consistentes.
 *  Deve validar sua integridade ao ser criada
 *
 * */

@Getter
public class Fucionamento {

    private String horaInicial;
    private String horaFinal;
    private Set<Integer> diasDaSemana;

    public Fucionamento(String horaInicial, String horaFinal, Set<Integer> diasDaSemana) {
        Objects.requireNonNull(horaInicial, "Hora inicial não pode estar null");
        Objects.requireNonNull(horaFinal, "Hora final não pode estar null");
        Objects.requireNonNull(diasDaSemana, "Dias da Semana não pode estar null");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime.parse(horaInicial, formatter);
            LocalTime.parse(horaFinal, formatter);
        } catch (DateTimeParseException ex) {
            throw new DomainException("Horarios de funcionamentos inválidos");
        }

        for (var dia : diasDaSemana) {
            if (dia < 1 || dia > 7) {
                throw new DomainException("Dias da semana inválidos, os dias devem ser de 1 a 7");
            }
        }

        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.diasDaSemana = diasDaSemana;
    }
}
