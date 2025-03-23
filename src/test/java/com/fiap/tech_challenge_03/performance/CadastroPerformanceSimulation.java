package com.fiap.tech_challenge_03.performance;


import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.input.CadastrarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.input.CadastrarUsuarioInput;
import com.fiap.tech_challenge_03.infra.adapter.Presenter;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class CadastroPerformanceSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .header("Content-Type", "application/json");

    private CadastrarRestauranteInput cadastrarRestauranteInput = RestauranteBuilder.cadastroInput();
    ActionBuilder cadastrarRestauranteUseCase = http("cadastrar restaurante")
            .post("/restaurantes")
            .body(StringBody(Presenter.jsonFrom(cadastrarRestauranteInput)))
            .check(status().is(201));
    ScenarioBuilder cenanarioCadastrarRestauranteUseCase = scenario("Cadastrar restaurante")
            .exec(cadastrarRestauranteUseCase);
    ActionBuilder buscarRestaurantesUseCase = http("buscar restaurante com parametros")
            .post("/restaurantes/buscar")
            .body(StringBody(Presenter.jsonFrom(BuscarRestauranteInput.builder()
                    .nome(cadastrarRestauranteInput.nome())
                    .tipoDeCozinha(cadastrarRestauranteInput.tipoDeCozinha())
                    .build())))
            .check(status().is(200));
    ScenarioBuilder cenarioBuscarRestauranteUseCase = scenario("Buscar restaurantes")
            .exec(buscarRestaurantesUseCase);
    private CadastrarUsuarioInput cadastrarUsuarioInput = UsuarioBuilder.cadastroInput();
    ActionBuilder cadastrarUsuarioUseCase = http("cadastrar usuario")
            .post("/usuarios")
            .body(StringBody(Presenter.jsonFrom(cadastrarUsuarioInput)))
            .check(status().is(201));
    ScenarioBuilder cenarioCadastrarUsuarioUseCae = scenario("Cadastrar usuários")
            .exec(cadastrarUsuarioUseCase);

    {
        setUp(
                cenanarioCadastrarRestauranteUseCase.injectOpen(
                        rampUsersPerSec(1)
                                .to(10)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(10)
                                .to(1)
                                .during(Duration.ofSeconds(10))),
                cenarioBuscarRestauranteUseCase.injectOpen(
                        rampUsersPerSec(1)
                                .to(30)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(30)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(30)
                                .to(1)
                                .during(Duration.ofSeconds(10))),
                cenarioCadastrarUsuarioUseCae.injectOpen(
                        rampUsersPerSec(1)
                                .to(10)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(10)
                                .to(1)
                                .during(Duration.ofSeconds(10)))
        ).protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(50),
                        global().failedRequests().count().is(0L)
                );
    }
}
