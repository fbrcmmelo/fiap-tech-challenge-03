package com.fiap.tech_challenge_03.application.cadastro.input;

import io.swagger.v3.oas.annotations.media.Schema;

public record BuscarRestauranteInput(@Schema(example = "MyRestaurant") String nome,
                                     @Schema(example = "City Name") String cidade,
                                     @Schema(example = "State Name") String estado,
                                     @Schema(example = "Adress ..") String logradouro,
                                     @Schema(example = "Italian food") String tipoDeCozinha) {
}