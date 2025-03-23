package com.fiap.tech_challenge_03.domain.avaliacao.entity;

import com.fiap.tech_challenge_03.domain.avaliacao.vo.Nota;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Avaliacao {

    private String id;
    private Nota nota;
    private String comentario;
    private Usuario avaliador;
    private Restaurante restaurante;

    public Avaliacao(String id, Integer nota, String comentario, Usuario avaliador, Restaurante restaurante) {
        Objects.requireNonNull(nota, "Nota não pode ser nula");
        Objects.requireNonNull(avaliador, "Avaliador não pode ser nulo");
        Objects.requireNonNull(restaurante, "Restaurante não pode ser nulo");

        this.id = id;
        this.nota = new Nota(nota);
        this.avaliador = avaliador;
        this.restaurante = restaurante;
        this.comentario = comentario;
    }
}
