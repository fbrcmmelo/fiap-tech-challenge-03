package com.fiap.tech_challenge_03.infra.config;

import com.fiap.tech_challenge_03.domain.IDomainEventPubGateway;
import com.fiap.tech_challenge_03.domain.avaliacao.gateway.IAvaliacaoGateway;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IRestauranteGateway;
import com.fiap.tech_challenge_03.domain.cadastro.gateway.IUsuarioGateway;
import com.fiap.tech_challenge_03.infra.avaliacao.gateway.AvaliacaoGatewayImpl;
import com.fiap.tech_challenge_03.infra.avaliacao.repository.AvaliacaoMongoRepository;
import com.fiap.tech_challenge_03.infra.cadastro.gateway.RestauranteGatewayImpl;
import com.fiap.tech_challenge_03.infra.cadastro.gateway.UsuarioGatewayImpl;
import com.fiap.tech_challenge_03.infra.cadastro.repository.RestauranteMongoRepository;
import com.fiap.tech_challenge_03.infra.cadastro.repository.UsuarioMongoRepository;
import com.fiap.tech_challenge_03.infra.gateway.DomainEventPubGatewayImpl;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    IDomainEventPubGateway domainEventPub(final ApplicationEventPublisher publisher) {
        return new DomainEventPubGatewayImpl(publisher);
    }

    @Bean
    IRestauranteGateway restauranteGateway(final RestauranteMongoRepository repository) {
        return new RestauranteGatewayImpl(repository);
    }

    @Bean
    IAvaliacaoGateway avaliacaoGateway(final AvaliacaoMongoRepository repository) {
        return new AvaliacaoGatewayImpl(repository);
    }

    @Bean
    IUsuarioGateway usuarioGateway(final UsuarioMongoRepository repository) {
        return new UsuarioGatewayImpl(repository);
    }

}
