package com.fiap.tech_challenge_03.infra.cadastro.api;

import com.fiap.tech_challenge_03.application.cadastro.usecase.ICadastrarRestauranteUseCase;
import com.fiap.tech_challenge_03.domain.cadastro.usecase.CadastrarRestauranteUseCase;
import com.fiap.tech_challenge_03.infra.adapter.Presenter;
import com.fiap.tech_challenge_03.infra.cadastro.adapter.RestauranteMapper;
import com.fiap.tech_challenge_03.utils.RestauranteBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RestauranteControllerTest {

    @Mock
    private ICadastrarRestauranteUseCase cadastrarUseCase;

    private MockMvc mockMvc;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        this.openMocks = MockitoAnnotations.openMocks(this);
        this.cadastrarUseCase = mock(CadastrarRestauranteUseCase.class);

        final var restauranteController = new RestauranteController(cadastrarUseCase);

        mockMvc = MockMvcBuilders.standaloneSetup(restauranteController).build();
    }

    @AfterEach
    void tearDown()
            throws Exception {
        openMocks.close();
    }

    @Test
    void deverPermitirCadastrarRestaurante()
            throws Exception {
        // Arrange
        var input = RestauranteBuilder.cadastroInput();
        var output = RestauranteBuilder.cadastroOutput();
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
}