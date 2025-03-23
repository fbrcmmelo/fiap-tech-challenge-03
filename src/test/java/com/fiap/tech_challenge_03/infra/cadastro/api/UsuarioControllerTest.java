package com.fiap.tech_challenge_03.infra.cadastro.api;

import com.fiap.tech_challenge_03.application.cadastro.usecase.ICadastrarUsuarioUseCase;
import com.fiap.tech_challenge_03.infra.adapter.Presenter;
import com.fiap.tech_challenge_03.utils.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController restauranteController;
    @Mock
    private ICadastrarUsuarioUseCase cadastrarUseCase;

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
    void deverPermitirCadastrarUsuario()
            throws Exception {
        // Arrange
        var input = UsuarioBuilder.cadastroInput();
        var output = UsuarioBuilder.output();
        var dto = UsuarioMapper.dtoFrom(output);

        when(cadastrarUseCase.execute(input)).thenReturn(output);

        // Act
        var response = mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Presenter.jsonFrom(input)));

        // Assert
        response.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.nome").value(dto.getNome()))
                .andExpect(jsonPath("$.email").value(dto.getEmail()));

        verify(cadastrarUseCase, times(1)).execute(input);
    }
}
