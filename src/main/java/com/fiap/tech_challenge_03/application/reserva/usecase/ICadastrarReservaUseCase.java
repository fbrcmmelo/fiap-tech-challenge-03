package com.fiap.tech_challenge_03.application.reserva.usecase;

import com.fiap.tech_challenge_03.application.IUseCaseIO;
import com.fiap.tech_challenge_03.application.reserva.input.CadastrarReservaInput;
import com.fiap.tech_challenge_03.application.reserva.output.ReservaOutput;

public interface ICadastrarReservaUseCase
    extends IUseCaseIO<CadastrarReservaInput, ReservaOutput> {}

