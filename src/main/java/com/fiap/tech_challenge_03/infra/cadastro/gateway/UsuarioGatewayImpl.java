package com.fiap.tech_challenge_03.infra.cadastro.gateway;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IUsuarioGateway;
import com.fiap.tech_challenge_03.infra.cadastro.entity.UsuarioJpaEntity;
import com.fiap.tech_challenge_03.infra.cadastro.repository.UsuarioMongoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UsuarioGatewayImpl implements IUsuarioGateway {

    private final UsuarioMongoRepository repository;

    @Override
    public Optional<Usuario> buscarPorId(String id) {
        return this.repository.findById(id).map(UsuarioJpaEntity::toUsuario);
    }

}
