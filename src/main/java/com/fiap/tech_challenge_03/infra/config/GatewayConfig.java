package com.fiap.tech_challenge_03.infra.config;

import com.fiap.tech_challenge_03.domain.cadastro.gateway.RestauranteGateway;
import com.fiap.tech_challenge_03.infra.cadastro.gateway.RestauranteGatewayImpl;
import com.fiap.tech_challenge_03.infra.cadastro.repository.RestauranteMongoRepository;
import com.fiap.tech_challenge_03.infra.gateway.DomainEventPubImpl;
import com.fiap.tech_challenge_03.infra.interfaces.IDomainEventPub;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    IDomainEventPub domainEventPub(final ApplicationEventPublisher publisher) {
        return new DomainEventPubImpl(publisher);
    }

    @Bean
    RestauranteGateway restauranteGateway(final RestauranteMongoRepository repository) {
        return new RestauranteGatewayImpl(repository);
    }

}
