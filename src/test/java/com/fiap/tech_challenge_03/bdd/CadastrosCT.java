package com.fiap.tech_challenge_03.bdd;

import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CadastrosCT {

    private static final String ENDPOINT = "http://localhost:8080";
    private Response response;
    private RestauranteDTO restauranteCadastrado;

    @When("submeter um novo restaurante")
    public RestauranteDTO submeterNovoRestaurante() {
        final var input = RestauranteBuilder.cadastroInput();
        response = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(ENDPOINT.concat("/restaurantes"));
        return response.then().extract().as(RestauranteDTO.class);
    }

    @Then("o restaurante é cadastrado com sucesso")
    public void restauranteCadastradoComSucesso() {
        final var input = RestauranteBuilder.cadastroInput();
        response.then()
                .statusCode(201)
                .body("$", Matchers.hasKey("id"))
                .body("$", Matchers.hasKey("nome"))
                .body("nome", Matchers.equalTo(input.nome()));
    }

    @Given("que haja restaurantes cadastrados")
    public void restauranteCadastrado() {
        this.restauranteCadastrado = this.submeterNovoRestaurante();
    }

    @When("submeter uma busca com parâmetros")
    public void buscarRestaurantePorParametros() {
        final var input = RestauranteBuilder.buscarComParametrosInput();
        response = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(ENDPOINT.concat("/restaurantes/buscar"));
    }

    @And("os parâmetros serem equivalentes à algum restaurante")
    public void parametrosDeBuscaEquivalantesComAlgumRestaurante() {
        final var input = RestauranteBuilder.buscarComParametrosInput();

        assertThat(input.nome())
                .isEqualTo(this.restauranteCadastrado.getNome());
    }

    @Then("os restaurantes encontrados serão exibidos")
    public void exibirRestaurantesEcontrados() {
        response.then()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0));
    }

    @When("submeter um novo usuario")
    public void submeterNovoUsuario() {
        final var input = UsuarioBuilder.cadastroInput();
        response = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(ENDPOINT.concat("/usuarios"));
    }

    @Then("o usuario é cadastrado com sucesso")
    public void usuarioCadastradoComSucesso() {
        final var input = UsuarioBuilder.cadastroInput();
        response.then()
                .statusCode(201)
                .body("$", Matchers.hasKey("id"))
                .body("$", Matchers.hasKey("nome"))
                .body("$", Matchers.hasKey("email"))
                .body("nome", Matchers.equalTo(input.nome()))
                .body("email", Matchers.equalTo(input.email()));
    }
}
