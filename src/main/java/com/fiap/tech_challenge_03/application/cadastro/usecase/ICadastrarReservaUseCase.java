package com.fiap.tech_challenge_03.application.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.dto.input.CadastrarReservaInput;
import com.fiap.tech_challenge_03.application.cadastro.dto.output.ReservaOutput;
import com.fiap.tech_challenge_03.infra.interfaces.IUseCaseIO;

public interface ICadastrarReservaUseCase
    extends IUseCaseIO<CadastrarReservaInput, ReservaOutput>{}

