package com.fiap.tech_challenge_03.infra.cadastro.api;

import com.fiap.tech_challenge_03.application.cadastro.usecase.IBuscarRestaurantesComParametrosUseCase;
import com.fiap.tech_challenge_03.application.cadastro.usecase.ICadastrarRestauranteUseCase;
import com.fiap.tech_challenge_03.infra.adapter.Presenter;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RestauranteControllerTest {

    @InjectMocks
    private RestauranteController restauranteController;
    @Mock
    private ICadastrarRestauranteUseCase cadastrarUseCase;
    @Mock
    private IBuscarRestaurantesComParametrosUseCase buscarRestaurantesUseCase;

    private MockMvc mockMvc;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        this.openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restauranteController).build();
    }

    @AfterEach
    void tearDown()
            throws Exception {
        this.openMocks.close();
    }

    @Test
    void deverPermitirCadastrarRestaurante()
            throws Exception {
        // Arrange
        var input = RestauranteBuilder.cadastroInput();
        var output = RestauranteBuilder.output();
        var dto = RestauranteMapper.dtoFrom(output);

        when(cadastrarUseCase.execute(input)).thenReturn(output);

        // Act
        var response = mockMvc.perform(post("/restaurantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Presenter.jsonFrom(input)));

        // Assert
        response.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.nome").value(dto.getNome()));

        verify(cadastrarUseCase, times(1)).execute(input);
    }

    @Test
    void deveEncontrarRestauranteQuandoRealizarBuscaComParametros()
            throws Exception {
        // Arrange
        var input = RestauranteBuilder.buscarComParametrosInput();
        var output = RestauranteBuilder.output();
        var dto = RestauranteMapper.dtoFrom(output);

        when(buscarRestaurantesUseCase.execute(input)).thenReturn(Collections.singletonList(output));

        // Act
        var response = mockMvc.perform(post("/restaurantes/buscar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Presenter.jsonFrom(input)));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(dto.getId()))
                .andExpect(jsonPath("$[0].nome").value(dto.getNome()));

        verify(buscarRestaurantesUseCase, times(1)).execute(input);
    }
}
