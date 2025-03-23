package com.fiap.tech_challenge_03.domain.cadastro.entity;

import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.domain.cadastro.vo.CPF;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Email;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Usuario {

    private String id;
    private String nome;
    private Email email;
    private String senha;
    private CPF cpf;

    public Usuario(String id, String nome, String cpf, String email, String senha) {
        Objects.requireNonNull(nome);
        Objects.requireNonNull(cpf);
        Objects.requireNonNull(email);
        Objects.requireNonNull(senha);

        if (nome.isBlank()) {
            throw new DomainException("Nome usuario não pode estar em branco");
        }

        if (nome.trim().length() < 3) {
            throw new DomainException("Nome usuario não pode ter menos que 3 characteres");
        }

        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = new Email(email);
        this.cpf = new CPF(cpf);
    }
}
