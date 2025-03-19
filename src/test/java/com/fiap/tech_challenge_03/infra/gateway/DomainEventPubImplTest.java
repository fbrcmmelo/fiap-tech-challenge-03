package com.fiap.tech_challenge_03.infra.gateway;

import com.fiap.tech_challenge_03.domain.cadastro.event.EventoRestauranteCadastrado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DomainEventPubImplTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private DomainEventPubGatewayImpl domainEventPub;

    @Captor
    private ArgumentCaptor<Object> eventCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void deverPublicarEvento() {
        // Arrange
        Object event = new EventoRestauranteCadastrado("ID");

        // Act
        domainEventPub.publish(event);

        // Assert
        verify(applicationEventPublisher, times(1)).publishEvent(event);

        // Assert
        verify(applicationEventPublisher).publishEvent(eventCaptor.capture());
        assertThat(eventCaptor.getValue()).isEqualTo(event);
    }
}