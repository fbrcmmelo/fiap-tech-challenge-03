package com.fiap.tech_challenge_03.infra.interfaces;

public interface IDomainEventPub {
    void publish(final Object event);
}
