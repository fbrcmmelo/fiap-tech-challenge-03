package com.fiap.tech_challenge_03.domain.cadastro.entity;

import lombok.Getter;

@Getter
public class Usuario {

    private String id;
    private String nome;

    public Usuario(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
