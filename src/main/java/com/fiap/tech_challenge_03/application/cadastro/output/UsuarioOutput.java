package com.fiap.tech_challenge_03.application.cadastro.output;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Email;

public record UsuarioOutput(String id,
                            String nome,
                            Email email) {

    public static UsuarioOutput from(Usuario usuario) {
        return new UsuarioOutput(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());
    }
}
