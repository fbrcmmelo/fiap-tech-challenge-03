package com.fiap.tech_challenge_03.application.cadastro.input;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

public record CadastrarRestauranteInput(@Schema(example = "MyRestaurant") String nome,
                                        @Schema(example = "City Name") String cidade,
                                        @Schema(example = "State Name") String estado,
                                        @Schema(description = "Número endereço", example = "123") int numero,
                                        @Schema(example = "Adress ..") String logradouro,
                                        @Schema(example = "Italian food") String tipoDeCozinha,
                                        @Schema(example = "09:00") String horaIniFuncionamento,
                                        @Schema(example = "15:00") String horaFimFuncionamento,
                                        @Schema(example = "[1,2,3,4]") Set<Integer> diasDaSemanaFunc,
                                        @Schema(example = "50") Integer quantidadeDeMesas) {
}
