package com.fiap.tech_challenge_03.domain.cadastro.entity;

import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Funcionamento;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Localidade;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Mesa;
import com.fiap.tech_challenge_03.domain.cadastro.vo.NomeRestaurante;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
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
    private Funcionamento funcionamento;
    private Integer quantidadeMesas;
    private String tipoDeCozinha;
    private List<Mesa> mesas = new ArrayList<>();

    public Restaurante(String id, String nome, Localidade localidade,
                       Funcionamento funcionamento, Integer quantidadeMesas, String tipoDeCozinha) {
        Objects.requireNonNull(nome);
        Objects.requireNonNull(localidade);
        Objects.requireNonNull(funcionamento);
        Objects.requireNonNull(quantidadeMesas);
        Objects.requireNonNull(tipoDeCozinha);

        if (quantidadeMesas <= 0) {
            throw new DomainException("Quantidade Mesas do restaurante não pode ser menor que 1");
        }

        if (tipoDeCozinha.isEmpty()) {
            throw new DomainException("Tipo de cozinha do restaurante não pode estar em branco");
        }

        this.id = id;
        this.nome = new NomeRestaurante(nome);
        this.localidade = localidade;
        this.funcionamento = funcionamento;
        this.quantidadeMesas = quantidadeMesas;
        this.tipoDeCozinha = tipoDeCozinha;

        for (int i = 1; i <= quantidadeMesas; i++) {
            this.mesas.add(new Mesa(i));
        }
    }

}
