package com.fiap.tech_challenge_03.domain;

public interface IDomainEventPubGateway {
    void publish(final Object event);
}
