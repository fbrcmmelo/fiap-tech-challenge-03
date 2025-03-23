package com.fiap.tech_challenge_03.domain.cadastro.event;

public record EventoUsuarioCadastrado(String id, String email) {

    @Override
    public String toString() {
        return "Usuario Cadastrado";
    }
}
