package com.fiap.tech_challenge_03.domain.cadastro.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String telefone;
    private String accountId;
//    private List<TokenJWT> jwtTokens;
    private List<Reserva> reservas;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Usuario(String nome, String email, String telefone, String accountId) {
        Objects.requireNonNull(nome, "Nome não pode ser nulo");
        Objects.requireNonNull(email, "Email não pode ser nulo");
        Objects.requireNonNull(telefone, "Telefone não pode ser nulo");
        Objects.requireNonNull(accountId, "AccountId não pode ser nulo");

        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.accountId = accountId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }




}
