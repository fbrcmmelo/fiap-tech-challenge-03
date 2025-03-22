package com.fiap.tech_challenge_03.infra.avaliacao.api;

import com.fiap.tech_challenge_03.infra.adapter.Presenter;
import com.fiap.tech_challenge_03.utils.AvaliacaoBuilder;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;

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
        //TODO: cadastrar restaurante e avaliador para gerar uma avaliacao
        var input = AvaliacaoBuilder.realizarAvaliacaoInput();

        // Act & Assert
        given()
                .contentType("application/json")
                .body(Presenter.jsonFrom(input))
                .when()
                .post("/avaliacoes")
                .then()
                .statusCode(201)
                .body("$", Matchers.hasKey("id"))
                .body("$", Matchers.hasKey("nome"))
                .body("$", Matchers.hasKey("nota"))
                .body("$", Matchers.hasKey("avaliador"))
                .body("$", Matchers.hasKey("restaurante"))
                .body("comentario", Matchers.equalTo(input.comentario()))
                .body("nota", Matchers.equalTo(input.nota()));
    }
}
