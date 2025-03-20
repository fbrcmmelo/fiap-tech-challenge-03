package com.fiap.tech_challenge_03.utils;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;

public class UsuarioBuilder {

    UsuarioBuilder() {
        throw new IllegalStateException("Classe Utilitaria");
    }

    public static Usuario entity() {
        return new Usuario("id", "nome");
    }
}
