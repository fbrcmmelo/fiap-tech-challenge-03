package com.fiap.tech_challenge_03.application.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.dto.input.CadastrarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.dto.output.CadastrarRestauranteOutput;
import com.fiap.tech_challenge_03.infra.interfaces.IUseCaseIO;

public interface ICadastrarRestauranteUseCase
        extends IUseCaseIO<CadastrarRestauranteInput, CadastrarRestauranteOutput> {
}
