package com.fiap.tech_challenge_03.infra.cadastro.event;

import com.fiap.tech_challenge_03.domain.cadastro.event.EventoUsuarioCadastrado;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UsuarioEventListener {

    @EventListener
    public void usuarioCadastrado(final EventoUsuarioCadastrado evento) {
        log.info("Restaurante cadastrado com sucesso, id: {}", evento.id());
        log.info("Enviando email de boas vindas para o email : {}", evento.email());
    }
}
