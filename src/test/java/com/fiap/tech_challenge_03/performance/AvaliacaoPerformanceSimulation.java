package com.fiap.tech_challenge_03.performance;

import com.fiap.tech_challenge_03.application.avaliacao.input.RealizarAvaliacaoInput;
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

public class AvaliacaoPerformanceSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .header("Content-Type", "application/json");
    ActionBuilder realizarAvaliacaoRestaurante = http("realizar avaliacao")
            .post("/avaliacoes")
            .body(StringBody(Presenter.jsonFrom(RealizarAvaliacaoInput.builder()
                    .nota(3)
                    .comentario("Performance test")
                    .build())))
            .check(status().is(201));
    ScenarioBuilder cenanarioRealizarAvaliacaoUseCase = scenario("Realizar avaliação")
            .exec(realizarAvaliacaoRestaurante);
    private CadastrarUsuarioInput cadastrarUsuarioInput = UsuarioBuilder.cadastroInput();
    ActionBuilder cadastrarUsuario = http("Cadastrar usuario teste")
            .post("/usuarios")
            .body(StringBody(Presenter.jsonFrom(cadastrarUsuarioInput)))
            .check(status().is(201))
            .check(jsonPath("$.id").saveAs("usuarioId"));
    ScenarioBuilder cenarioCadastrarUsuarioUseCase = scenario("Cadastrar usuários")
            .exec(cadastrarUsuario);
    private CadastrarRestauranteInput cadastrarRestauranteInput = RestauranteBuilder.cadastroInput();
    ActionBuilder cadastrarRestaurante = http("cadastrar restaurante")
            .post("/restaurantes")
            .body(StringBody(Presenter.jsonFrom(cadastrarRestauranteInput)))
            .check(status().is(201))
            .check(jsonPath("$.id").saveAs("restauranteId"));
    ScenarioBuilder cenanarioCadastrarRestauranteUseCase = scenario("Cadastrar restaurante")
            .exec(cadastrarRestaurante);

    {


        setUp(
                cenanarioCadastrarRestauranteUseCase.injectOpen(
                        rampUsersPerSec(1)
                                .to(1)
                                .during(Duration.ofSeconds(1))),
                cenarioCadastrarUsuarioUseCase.injectOpen(
                        rampUsersPerSec(1)
                                .to(10)
                                .during(Duration.ofSeconds(1))),
                cenanarioRealizarAvaliacaoUseCase.injectOpen(
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
