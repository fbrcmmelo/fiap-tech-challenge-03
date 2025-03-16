package com.fiap.tech_challenge_03.application.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.dto.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.dto.output.RestauranteOutput;
import com.fiap.tech_challenge_03.infra.interfaces.IUseCaseIO;

import java.util.Collection;

public interface IBuscarRestaurantesUseCase
        extends IUseCaseIO<BuscarRestauranteInput, Collection<RestauranteOutput>> {
}
