package com.fiap.tech_challenge_03.application.cadastro.input;

import io.swagger.v3.oas.annotations.media.Schema;

public record CadastrarUsuarioInput(@Schema(example = "Teste Testano de Testado") String nome,
                                    @Schema(example = "teste@testmail.com") String email,
                                    String senha,
                                    @Schema(example = "12312312323") String cpf) {
}
