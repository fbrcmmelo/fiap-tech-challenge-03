package com.fiap.tech_challenge_03.domain.cadastro.gateway;

import com.fiap.tech_challenge_03.application.cadastro.dto.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;

import java.util.List;

public interface IRestauranteGateway {

    Restaurante cadastrar(Restaurante restaurante);

    List<Restaurante> buscarComParametros(BuscarRestauranteInput input);
}
