package com.fiap.tech_challenge_03.bdd;

import com.fiap.tech_challenge_03.application.cadastro.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;

public class CadastrosCT {

    private final static String ENDPOINT = "http://localhost:8080/restaurantes";
    private Response response;

    @When("submeter um novo restaurante")
    public RestauranteDTO submeterNovoRestaurante() {
        final var input = RestauranteBuilder.cadastroInput();
        response = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(ENDPOINT);
        return response.then().extract().as(RestauranteDTO.class);
    }

    @Then("o restaurante é cadastrado com sucesso")
    public void restauranteCadastradoComSucesso() {
        final var input = RestauranteBuilder.cadastroInput();
        response.then()
                .statusCode(201)
                .statusCode(201)
                .body("$", Matchers.hasKey("id"))
                .body("$", Matchers.hasKey("nome"))
                .body("nome", Matchers.equalTo(input.nome()));
    }

}
