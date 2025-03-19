package com.fiap.tech_challenge_03.domain.cadastro.vo;

import com.fiap.tech_challenge_03.domain.DomainException;
import lombok.Getter;

import java.util.Objects;

/*
 *  Value Object -> Imutável, criação de um dado consistente e integro
 *  Ajuda na construção de entidades consistentes.
 *  Deve validar sua integridade ao ser criada
 *
 * */

@Getter
public class Localidade {

    private Integer numero;
    private String lougradouro;
    private String cidade;
    private String estado;

    public Localidade(Integer numero, String lougradouro, String cidade, String estado) {
        Objects.requireNonNull(numero, "Numero não pode estar null");
        Objects.requireNonNull(lougradouro, "Logradouro não pode estar null");
        Objects.requireNonNull(cidade, "Cidade não pode estar null");
        Objects.requireNonNull(estado, "Estado não pode estar null");

        validate(lougradouro, cidade, estado);

        this.numero = numero;
        this.lougradouro = lougradouro;
        this.cidade = cidade;
        this.estado = estado;
    }

    private static void validate(String lougradouro, String cidade, String estado) {
        if (lougradouro.isEmpty()) {
            throw new DomainException("Logradouro não pode estar em branco");
        }

        if (cidade.isEmpty()) {
            throw new DomainException("Cidade não pode estar em branco");
        }

        if (estado.isEmpty()) {
            throw new DomainException("Estado não pode estar em branco");
        }
    }

}
