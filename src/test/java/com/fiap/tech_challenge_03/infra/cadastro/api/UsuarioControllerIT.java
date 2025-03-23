package com.fiap.tech_challenge_03.infra.cadastro.api;

import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsuarioControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deverPermitirCadastrarUsuario() {
        // Arrange
        var input = UsuarioBuilder.cadastroInput();

        // Act & Assert
        given()
                .contentType("application/json")
                .body(input)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("$", Matchers.hasKey("id"))
                .body("$", Matchers.hasKey("nome"))
                .body("$", Matchers.hasKey("email"))
                .body("nome", Matchers.equalTo(input.nome()))
                .body("email", Matchers.equalTo(input.email()));
    }
}