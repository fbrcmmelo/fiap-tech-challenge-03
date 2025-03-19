package com.fiap.tech_challenge_03.domain.cadastro.gateway;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;

import java.util.List;
import java.util.Optional;

public interface IRestauranteGateway {

    Restaurante cadastrar(Restaurante restaurante);
    List<Restaurante> buscarComParametros(BuscarRestauranteInput input);

    Optional<Restaurante> buscarPorId(String id);
}
