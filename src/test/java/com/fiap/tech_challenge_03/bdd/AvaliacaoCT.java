package com.fiap.tech_challenge_03.bdd;

import com.fiap.tech_challenge_03.application.avaliacao.input.RealizarAvaliacaoInput;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;
import com.fiap.tech_challenge_03.infra.cadastro.api.dto.UsuarioDTO;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AvaliacaoCT {

    private static final String URL = "http://localhost:8080";
    private Response response;
    private RestauranteDTO restauranteCadastrado;
    private UsuarioDTO usuarioCadastrado;

    @Given("que eu tenha um restaurante cadastrado")
    public void temRestauranteCadastrado() {
        this.restauranteCadastrado = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(RestauranteBuilder.cadastroInput())
                .when()
                .post(URL.concat("/restaurantes"))
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(RestauranteDTO.class);

        assertThat(this.restauranteCadastrado)
                .isNotNull()
                .isInstanceOf(RestauranteDTO.class);
    }

    @And("um usuario cadastrado")
    public void temUsuarioCadastrado() {
        this.usuarioCadastrado = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(UsuarioBuilder.cadastroInput())
                .when()
                .post(URL.concat("/usuarios"))
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(UsuarioDTO.class);

        assertThat(this.usuarioCadastrado)
                .isNotNull()
                .isInstanceOf(UsuarioDTO.class);
    }

    @When("o usuario submeter uma nova avaliação ao restaurante")
    public void submeterNovaAvaliacao() {
        final var input = RealizarAvaliacaoInput.builder()
                .comentario("System test working")
                .nota(4)
                .usuarioId(usuarioCadastrado.getId())
                .restauranteId(restauranteCadastrado.getId())
                .build();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(input)
                .when()
                .post(URL.concat("/avaliacoes"));
    }

    @Then("a avaliação é cadastrada com sucesso")
    public void avaliacaoCadastradaComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body("$", Matchers.hasKey("id"))
                .body("$", Matchers.hasKey("nota"))
                .body("$", Matchers.hasKey("comentario"))
                .body("$", Matchers.hasKey("avaliadorDto"))
                .body("$", Matchers.hasKey("restauranteDto"))
                .body("comentario", Matchers.notNullValue())
                .body("nota", Matchers.notNullValue())
                .body("avaliadorDto.nome", Matchers.equalTo(usuarioCadastrado.getNome()))
                .body("restauranteDto.nome", Matchers.equalTo(restauranteCadastrado.getNome()));
    }
}
