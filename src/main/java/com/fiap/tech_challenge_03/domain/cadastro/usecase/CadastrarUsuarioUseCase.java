package com.fiap.tech_challenge_03.domain.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.input.CadastrarUsuarioInput;
import com.fiap.tech_challenge_03.application.cadastro.output.UsuarioOutput;
import com.fiap.tech_challenge_03.application.cadastro.usecase.ICadastrarUsuarioUseCase;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;
import com.fiap.tech_challenge_03.domain.cadastro.service.UsuarioDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastrarUsuarioUseCase implements ICadastrarUsuarioUseCase {

    private final UsuarioDomainService domainService;

    @Override
    public UsuarioOutput execute(CadastrarUsuarioInput input) {
        final var usuario = this.domainService.cadastrar(new Usuario(null, input.nome(), input.cpf(), input.email(),
                input.senha()));

        return UsuarioOutput.from(usuario);
    }
}
