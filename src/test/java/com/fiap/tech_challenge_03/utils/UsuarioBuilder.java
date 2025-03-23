package com.fiap.tech_challenge_03.utils;

import com.fiap.tech_challenge_03.application.cadastro.input.CadastrarUsuarioInput;
import com.fiap.tech_challenge_03.application.cadastro.output.UsuarioOutput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;

public class UsuarioBuilder {

    UsuarioBuilder() {
        throw new IllegalStateException("Classe Utilitaria");
    }

    public static Usuario entity() {
        return new Usuario("id", "nome", "12312312312", "email", "seha");
    }

    public static CadastrarUsuarioInput cadastroInput() {
        return new CadastrarUsuarioInput("test", "test@email.com", "senhaTest", "12312312312");
    }

    public static UsuarioOutput output() {
        final var user = entity();
        return new UsuarioOutput(user.getId(), user.getNome(), user.getEmail());
    }
}
