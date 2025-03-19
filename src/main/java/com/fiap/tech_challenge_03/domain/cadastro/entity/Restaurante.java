package com.fiap.tech_challenge_03.domain.cadastro.entity;

import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Fucionamento;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Localidade;
import com.fiap.tech_challenge_03.domain.cadastro.vo.NomeRestaurante;
import lombok.Getter;

import java.util.Objects;

/*
 *  Entidade -> Nucleo do dominio
 *  Deve validar sua integridade ao ser criada
 *
 * */

@Getter
public class Restaurante {

    private String id;
    private NomeRestaurante nome;
    private Localidade localidade;
    private Fucionamento funcionamento;
    private Integer capacidade;
    private String tipoDeCozinha;

    public Restaurante(String nome, Localidade localidade,
                       Fucionamento funcionamento, Integer capacidade, String tipoDeCozinha) {
        Objects.requireNonNull(nome);
        Objects.requireNonNull(localidade);
        Objects.requireNonNull(funcionamento);
        Objects.requireNonNull(capacidade);
        Objects.requireNonNull(tipoDeCozinha);

        if (capacidade <= 0) {
            throw new DomainException("Capacidade do restaurante não pode ser menor que 1");
        }

        if (tipoDeCozinha.isEmpty()) {
            throw new DomainException("Tipo de cozinha do restaurante não pode estar em branco");
        }

        this.nome = new NomeRestaurante(nome);
        this.localidade = localidade;
        this.funcionamento = funcionamento;
        this.capacidade = capacidade;
        this.tipoDeCozinha = tipoDeCozinha;
    }

}
