package com.fiap.tech_challenge_03.application.cadastro.usecase;

import com.fiap.tech_challenge_03.application.IUseCaseIO;
import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.output.RestauranteOutput;

import java.util.Collection;

public interface IBuscarRestaurantesComParametrosUseCase
        extends IUseCaseIO<BuscarRestauranteInput, Collection<RestauranteOutput>> {
}
