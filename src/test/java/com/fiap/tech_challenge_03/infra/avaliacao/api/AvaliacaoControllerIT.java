package com.fiap.tech_challenge_03.infra.avaliacao.api;

import com.fiap.tech_challenge_03.application.avaliacao.input.RealizarAvaliacaoInput;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.UsuarioDTO;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AvaliacaoControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = this.port;
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deveRealizarCasoDeUsoERetornarOutputParaApi() {
        // Arrange
        var usuarioInput = UsuarioBuilder.cadastroInput();
        var restauranteInput = RestauranteBuilder.cadastroInput();

        final var usuarioCadastrado = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(usuarioInput)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .extract().as(UsuarioDTO.class);

        final var restauranteCadastrado = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restauranteInput)
                .when()
                .post("/restaurantes")
                .then()
                .statusCode(201)
                .extract().as(RestauranteDTO.class);

        var avaliacaoInput = RealizarAvaliacaoInput.builder()
                .comentario("Teste comentado")
                .nota(5)
                .usuarioId(usuarioCadastrado.getId())
                .restauranteId(restauranteCadastrado.getId())
                .build();

        // Act Assert
        given()
                .contentType("application/json")
                .body(avaliacaoInput)
                .when()
                .post("/avaliacoes")
                .then()
                .statusCode(201)
                .body("$", Matchers.hasKey("id"))
                .body("$", Matchers.hasKey("nota"))
                .body("$", Matchers.hasKey("comentario"))
                .body("$", Matchers.hasKey("avaliadorDto"))
                .body("$", Matchers.hasKey("restauranteDto"))
                .body("comentario", Matchers.equalTo(avaliacaoInput.comentario()))
                .body("nota", Matchers.equalTo(avaliacaoInput.nota()))
                .body("avaliadorDto.nome", Matchers.equalTo(usuarioCadastrado.getNome()))
                .body("restauranteDto.nome", Matchers.equalTo(restauranteCadastrado.getNome()));
    }
}
