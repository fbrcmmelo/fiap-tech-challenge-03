package com.fiap.tech_challenge_03.domain.cadastro.gateway;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;

import java.util.Optional;

public interface IUsuarioGateway {
    Optional<Usuario> buscarPorId(String id);
}
