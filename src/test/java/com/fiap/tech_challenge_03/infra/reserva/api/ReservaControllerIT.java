package com.fiap.tech_challenge_03.infra.reserva.api;

import com.fiap.tech_challenge_03.application.reserva.input.CadastrarReservaInput;
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
class ReservaControllerIT {

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

        var reservaInput = CadastrarReservaInput.builder()
                .data("2025-04-01T19:00:00")
                .numeroPessoas(4)
                .usuarioId(usuarioCadastrado.getId())
                .restauranteId(restauranteCadastrado.getId())
                .build();

        // Act Assert
        given()
                .contentType("application/json")
                .body(reservaInput)
                .when()
                .post("/reservas")
                .then()
                .statusCode(201)
                .body("$", Matchers.hasKey("id"))
                .body("$", Matchers.hasKey("dataHora"))
                .body("$", Matchers.hasKey("quantidadePessoas"))
                .body("$", Matchers.hasKey("usuarioDto"))
                .body("$", Matchers.hasKey("restauranteDto"))
                .body("dataHora", Matchers.equalTo(reservaInput.data()))
                .body("quantidadePessoas", Matchers.equalTo(reservaInput.numeroPessoas()))
                .body("usuarioDto.nome", Matchers.equalTo(usuarioCadastrado.getNome()))
                .body("restauranteDto.nome", Matchers.equalTo(restauranteCadastrado.getNome()));
    }
}
