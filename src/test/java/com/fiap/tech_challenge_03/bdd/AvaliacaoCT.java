package com.fiap.tech_challenge_03.bdd;

import com.fiap.tech_challenge_03.infra.cadastro.api.dto.RestauranteDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class AvaliacaoCT {

    private static final String ENDPOINT = "http://localhost:8080/avaliacoes";
    private Response response;
    private RestauranteDTO restauranteCadastrado;
    private Object usuarioCadastrado;

    @Given("que eu tenha um restaurante cadastrado")
    public RestauranteDTO temRestauranteCadastrado() {
        return null;
    }

    @And("um usuario cadastrado")
    public Object temUsuarioCadastrado() {
        return null;
    }

    @When("o usuario submeter uma nova avaliação ao restaurante")
    public RestauranteDTO submeterNovaAvaliacao() {
        return null;
    }

    @And("a avaliacao for válida")
    public void avaliacaoValida() {

    }

    @Then("a avaliação é cadastrada com sucesso")
    public void avaliacaoCadastradaComSucesso() {
    }
}
