package com.fiap.tech_challenge_03.infra.reserva.api;

import com.fiap.tech_challenge_03.application.reserva.input.CadastrarReservaInput;
import com.fiap.tech_challenge_03.domain.reserva.usecase.CadastrarReservaUseCase;
import com.fiap.tech_challenge_03.domain.cadastro.usecase.CadastrarUsuarioUseCase;
import com.fiap.tech_challenge_03.domain.cadastro.usecase.CadastrarRestauranteUseCase;
import com.fiap.tech_challenge_03.infra.adapter.Presenter;
import com.fiap.tech_challenge_03.utils.ReservaBuilder;
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

class ReservaControllerTest {

    @Mock
    private CadastrarReservaUseCase realizarReservaUseCase;
    @Mock
    private CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
    @Mock
    private CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

    @InjectMocks
    private ReservaController reservaController;
    private MockMvc mockMvc;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        this.openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservaController).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        this.openMocks.close();
    }

    @Test
    void deveRealizarCasoDeUsoERetornarOutputParaApi() throws Exception {
        // Arrange
        final var input = ReservaBuilder.cadastrarReservaInput();
        final var output = ReservaBuilder.output();
        final var dto = ReservaMapper.dtoFrom(output);

        when(realizarReservaUseCase.execute(any(CadastrarReservaInput.class))).thenReturn(output);

        // Act and Assert
        mockMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Presenter.jsonFrom(input)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.usuarioId").value(dto.getUsuarioId()))
                .andExpect(jsonPath("$.restauranteId").value(dto.getRestauranteId()))
                .andExpect(jsonPath("$.data").value(dto.getData()));
    }
}
