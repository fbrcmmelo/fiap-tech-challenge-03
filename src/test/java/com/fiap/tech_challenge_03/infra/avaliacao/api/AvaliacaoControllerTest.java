package com.fiap.tech_challenge_03.infra.avaliacao.api;

import com.fiap.tech_challenge_03.application.avaliacao.input.RealizarAvaliacaoInput;
import com.fiap.tech_challenge_03.domain.avaliacao.usecase.RealizarAvaliacaoUseCase;
import com.fiap.tech_challenge_03.infra.adapter.Presenter;
import com.fiap.tech_challenge_03.utils.AvaliacaoBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AvaliacaoControllerTest {

    @Mock
    private RealizarAvaliacaoUseCase useCase;

    @InjectMocks
    private AvaliacaoController avaliacaoController;
    private MockMvc mockMvc;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        this.openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(avaliacaoController).build();
    }

    @AfterEach
    void tearDown()
            throws Exception {
        this.openMocks.close();
    }

    @Test
    void deveRealizarCasoDeUsoERetornarOutputParaApi()
            throws Exception {
        // Arrange
        final var input = AvaliacaoBuilder.realizarAvaliacaoInput();
        final var output = AvaliacaoBuilder.output();
        final var dto = AvaliacaoMapper.dtoFrom(output);

        when(useCase.execute(any(RealizarAvaliacaoInput.class))).thenReturn(output);

        // Act and Assert
        mockMvc.perform(post("/avaliacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Presenter.jsonFrom(input)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.nota").value(dto.getNota()))
                .andExpect(jsonPath("$.comentario").value(dto.getComentario()));
    }
}