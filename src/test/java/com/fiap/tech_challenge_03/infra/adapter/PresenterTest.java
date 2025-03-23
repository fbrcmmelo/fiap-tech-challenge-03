package com.fiap.tech_challenge_03.infra.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tech_challenge_03.application.ApplicationException;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PresenterTest {

    @Test
    void deveLancarException_QuandoTentarInstanciar_ClassePresenter() {
        assertThatThrownBy(Presenter::new)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Utility class");
    }

    @Test
    void deveRetornarStringDoObjeto() {
        // Arrange
        SampleResponse response = new SampleResponse("Test", 123); // Assuming a class for testing

        // Act
        String jsonResult = Presenter.jsonFrom(response);

        // Assert
        assertThat(jsonResult).isEqualTo("{\"name\":\"Test\",\"value\":123}");
    }

    @Test
    void deveLancarApplicationExceptionQuandoErroAoDesearelizarObjetoParaString()
            throws JsonProcessingException {
        // Arrange
        ObjectMapper mockObjectMapper = Mockito.spy(new ObjectMapper());

        // Configure the mock to throw JsonProcessingException
        Mockito.doThrow(new JsonProcessingException("Error processing JSON") {
                })
                .when(mockObjectMapper)
                .writeValueAsString(Mockito.any());

        // Act & Assert
        Object response = new Object() {
        };
        assertThatThrownBy(() -> Presenter.jsonFrom(response))
                .isInstanceOf(ApplicationException.class);
    }

    @Getter
    private static class SampleResponse {
        private String name;
        private int value;

        SampleResponse(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }
}