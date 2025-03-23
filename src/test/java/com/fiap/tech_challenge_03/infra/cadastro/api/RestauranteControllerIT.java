package com.fiap.tech_challenge_03.infra.cadastro.api;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
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
        final var restauranteInput = RestauranteBuilder.cadastroInput();
        final var restauranteCadastrado = given()
                .contentType("application/json")
                .body(restauranteInput)
                .when()
                .post("/restaurantes")
                .then()
                .statusCode(201)
                .extract().as(RestauranteDTO.class);

        var input = BuscarRestauranteInput.builder()
                .estado(restauranteCadastrado.getLocalidade().getEstado())
                .nome(restauranteCadastrado.getNome())
                .build();

        // Act & Assert
        final var restauranteDTOS = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post("/restaurantes/buscar")
                .then()
                .statusCode(200)
                .extract().as(RestauranteDTO[].class);

        assertThat(restauranteDTOS)
                .isNotNull()
                .hasSizeGreaterThan(1)
                .anySatisfy(restauranteDTO -> {
                    assertThat(restauranteDTO.getNome())
                            .isEqualTo(restauranteCadastrado.getNome());
                    assertThat(restauranteDTO.getLocalidade().getEstado())
                            .isEqualTo(restauranteCadastrado.getLocalidade().getEstado());
                });
    }
}