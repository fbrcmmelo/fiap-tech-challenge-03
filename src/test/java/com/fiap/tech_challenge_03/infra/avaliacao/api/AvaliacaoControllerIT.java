package com.fiap.tech_challenge_03.infra.avaliacao.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AvaliacaoControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deveRealizarCasoDeUsoERetornarOutputParaApi()
            throws Exception {
        // Arrange
//        final var output = AvaliacaoBuilder.output();
//        final var dto = AvaliacaoMapper.dtoFrom(output);
//        when(useCase.execute(any(RealizarAvaliacaoInput.class))).thenReturn(output);
//
//        // Act and Assert
//        mockMvc.perform(post("/avaliacoes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(Presenter.jsonFrom(input)))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(dto.getId()))
//                .andExpect(jsonPath("$.nota").value(dto.getNota()))
//                .andExpect(jsonPath("$.comentario").value(dto.getComentario()));
    }
}
