package com.fiap.tech_challenge_03.infra.cadastro.api;

import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestauranteControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deverPermitirCadastrarRestaurante() {
        // Arrange
        var input = RestauranteBuilder.cadastroInput();

        // Act & Assert
        given()
                .contentType("application/json")
                .body(input)
                .when()
                .post("/restaurantes")
                .then()
                .statusCode(201)
                .body("$", Matchers.hasKey("id"))
                .body("$", Matchers.hasKey("nome"))
                .body("nome", Matchers.equalTo(input.nome()));
    }

    @Test
    void deveEncontrarRestauranteQuandoRealizarBuscaComParametros() {
        // Arrange
        var input = RestauranteBuilder.buscarComParametrosInput();

        // Act & Assert
        given()
                .contentType("application/json")
                .body(input)
                .when()
                .post("/restaurantes/buscar")
                .then()
                .statusCode(200)
                .body("$", Matchers.hasSize(0));
//                .body("$[0]", Matchers.hasKey("id"))
//                .body("$[0]", Matchers.hasKey("nome"))
//                .body("[0].nome", Matchers.equalTo(input.nome()));

        //TODO: Ajustar apos integracao com repositorio, esperar receber objetos
    }
}