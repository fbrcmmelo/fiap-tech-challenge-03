package com.fiap.tech_challenge_03.domain.cadastro.service;

import com.fiap.tech_challenge_03.domain.IDomainEventPubGateway;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;
import com.fiap.tech_challenge_03.domain.cadastro.event.EventoUsuarioCadastrado;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IUsuarioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDomainService {

    private final IUsuarioGateway gateway;
    private final IDomainEventPubGateway eventPubGateway;

    public Usuario cadastrar(Usuario usuario) {
        final var usuarioCadastrado = this.gateway.cadastrar(usuario);

        this.eventPubGateway.publish(new EventoUsuarioCadastrado(usuarioCadastrado.getId(),
                usuarioCadastrado.getEmail().getValue()));

        return usuarioCadastrado;
    }
}
